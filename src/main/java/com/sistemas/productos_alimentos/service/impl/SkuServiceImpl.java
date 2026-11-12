package com.sistemas.productos_alimentos.service.impl;


import com.sistemas.productos_alimentos.models.Existencia;
import com.sistemas.productos_alimentos.models.ExistenciaId;
import com.sistemas.productos_alimentos.models.Periodo;
import com.sistemas.productos_alimentos.models.Sku;
import com.sistemas.productos_alimentos.repository.ExistenciaRepository;
import com.sistemas.productos_alimentos.repository.PeriodoRepository;
import com.sistemas.productos_alimentos.repository.SkuRepository;
import com.sistemas.productos_alimentos.service.SkuService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SkuServiceImpl implements SkuService {

    private final SkuRepository skuRepository;
    private final ExistenciaRepository existenciaRepository;
    private final PeriodoRepository periodoRepository;

    // El constructor DEBE recibir los 3 y asignarlos con 'this.'
    public SkuServiceImpl(SkuRepository skuRepository,
                          ExistenciaRepository existenciaRepository,
                          PeriodoRepository periodoRepository) {
        this.skuRepository = skuRepository;
        this.existenciaRepository = existenciaRepository;
        this.periodoRepository = periodoRepository; // <- Si esto faltaba, causaba el error
    }


    @Override
    public List<Sku> obtenerTodos() {
        return skuRepository.findAll();
    }

    @Override
    public List<Sku> obtenerActivos() {
        return skuRepository.findByActivo(1);
    }

    @Override
    public Sku buscarPorId(Integer skuId) {
        return skuRepository.findById(skuId).orElse(null);
    }

    @Override
    public List<Sku> buscarPorDescripcion(String descripcion) {
        return skuRepository.findByDescripcionContainingIgnoreCase(descripcion);
    }

    @Override
    public List<Sku> buscarPorCategoria(Integer catId) {
        return skuRepository.findByCategoria_CatId(catId);
    }

    @Override
    public Sku guardar(Sku sku) {

        Periodo periodoActivo = periodoRepository.findByEsActual(1)
                .orElseThrow(() -> new RuntimeException("No se encontró un periodo activo (esactual = 1) en la base de datos."));

        Sku skuGuardado = skuRepository.save(sku);


        // C. Construimos la Llave Compuesta (EmbeddedId) usando el Integer de idPeriodo
        ExistenciaId existenciaId = new ExistenciaId();
        existenciaId.setSku(skuGuardado.getSku()); // ID generado
        existenciaId.setPeriodo(periodoActivo.getIdPeriodo()); // El Integer del periodo actual de la BD

        // 3. Extraer y ejecutar la creación de la existencia
        Existencia nuevaExistencia = crearExistenciaInicial(skuGuardado, periodoActivo);

        existenciaRepository.save(nuevaExistencia);



        return skuGuardado;
    }
    /**
     * Método extraído: Se encarga exclusivamente de construir y mapear
     * la entidad Existencia inicial con su llave compuesta.
     */
    private Existencia crearExistenciaInicial(Sku sku, Periodo periodo) {
        // Construir la Llave Compuesta (EmbeddedId)
        ExistenciaId existenciaId = new ExistenciaId();
        existenciaId.setSku(sku.getSku());
        existenciaId.setPeriodo(periodo.getIdPeriodo());

        // Instanciar y rellenar el objeto con los valores iniciales
        Existencia existencia = new Existencia();
        existencia.setId(existenciaId);
        existencia.setInvInicial(0.0);
        existencia.setEntradas(0.0);
        existencia.setSalidas(0.0);
        existencia.setMerma(0.0);
        existencia.setInvFinal(0.0);
        existencia.setCostoUnitario(sku.getCostoUnitario());

        // Mapear datos de auditoría heredados del SKU
        existencia.setTimestamp(sku.getTimestamp());
        existencia.setUsuario(sku.getUsuario());

        return existencia;
    }


    @Override
    public Sku editar(Integer skuId, Sku detalles) {
        Optional<Sku> opcion = skuRepository.findById(skuId);
        if (opcion.isPresent()) {
            Sku existente = opcion.get();

            existente.setCategoria(detalles.getCategoria());
            existente.setMarca(detalles.getMarca());
            existente.setDescripcion(detalles.getDescripcion());
            existente.setPresentacion(detalles.getPresentacion());
            existente.setUnidadMedida(detalles.getUnidadMedida());
            existente.setEmpaque(detalles.getEmpaque());
            existente.setCostoUnitario(detalles.getCostoUnitario());
            existente.setActivo(detalles.getActivo());
            existente.setTimestamp(detalles.getTimestamp());
            existente.setUsuario(detalles.getUsuario());

            return skuRepository.save(existente);
        }
        return null;
    }

    @Override
    public boolean eliminar(Integer skuId) {
        if (skuRepository.existsById(skuId)) {
            skuRepository.deleteById(skuId);
            return true;
        }
        return false;
    }
}
