package com.sistemas.productos_alimentos.service.impl;

import com.sistemas.productos_alimentos.dto.ExistenciaResponseDTO;
import com.sistemas.productos_alimentos.models.Existencia;
import com.sistemas.productos_alimentos.models.ExistenciaId;
import com.sistemas.productos_alimentos.models.Periodo;
import com.sistemas.productos_alimentos.repository.ExistenciaRepository;
import com.sistemas.productos_alimentos.service.ExistenciaService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
public class ExistenciaServiceImpl implements ExistenciaService {

    private final ExistenciaRepository existenciaRepository;

    public ExistenciaServiceImpl(ExistenciaRepository existenciaRepository) {
        this.existenciaRepository = existenciaRepository;
    }



    @Override
    public Existencia buscarPorId(ExistenciaId id) {
        return existenciaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Existencia> buscarPorPeriodo(Integer periodo) {
        return existenciaRepository.findById_Periodo(periodo);
    }

    @Override
    public List<Existencia> buscarPorSku(Integer sku) {
        return existenciaRepository.findById_Sku(sku);
    }

    @Override
    public Existencia guardar(Existencia existencia) {
        return existenciaRepository.save(existencia);
    }

    @Override
    public Existencia editar(ExistenciaId id, Existencia detalles) {
        Optional<Existencia> opcion = existenciaRepository.findById(id);
        if (opcion.isPresent()) {
            Existencia existente = opcion.get();

            // Las columnas de auditoría y métricas de control de stock
            existente.setInvInicial(detalles.getInvInicial());
            existente.setEntradas(detalles.getEntradas());
            existente.setSalidas(detalles.getSalidas());
            existente.setMerma(detalles.getMerma());
            existente.setCostoUnitario(detalles.getCostoUnitario());
            existente.setInvFinal(detalles.getInvFinal());
            existente.setTimestamp(detalles.getTimestamp());
            existente.setUsuario(detalles.getUsuario());

            return existenciaRepository.save(existente);
        }
        return null;
    }

    @Override
    public boolean eliminar(ExistenciaId id) {
        if (existenciaRepository.existsById(id)) {
            existenciaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<ExistenciaResponseDTO> obtenerTodas() {
        List<Object[]> filasCrudas = existenciaRepository.obtenerTodasConDescripcionRaw();

        // Convertimos el Object[] manual usando el constructor del DTO
        return filasCrudas.stream()
                .map(row -> new ExistenciaResponseDTO())
                .toList();
    }

    @Override
    public List<ExistenciaResponseDTO> obtenerExistenciasDelPeriodo(Integer periodoActivo) {
        // 1. Consultamos a la base de datos filtrando por el periodo
        List<Existencia> lista = existenciaRepository.findById_Periodo(periodoActivo);

        // 2. Mapeamos cada entidad al DTO correspondiente
        return lista.stream().map(existencia -> {
            ExistenciaResponseDTO dto = new ExistenciaResponseDTO();

            // Extraemos los datos de la clave compuesta (@EmbeddedId)
            dto.setSku(Long.valueOf(existencia.getId().getSku()));
            dto.setPeriodo(existencia.getId().getPeriodo());

            // Mapeamos el resto de los campos de la tabla
            dto.setInvInicial(BigDecimal.valueOf(existencia.getInvInicial()));
            dto.setEntradas(BigDecimal.valueOf(existencia.getEntradas()));
            dto.setSalidas(BigDecimal.valueOf(existencia.getSalidas()));
            dto.setMerma(BigDecimal.valueOf(existencia.getMerma()));
            dto.setCostoUnitario(existencia.getCostoUnitario());
            dto.setInvFinal(BigDecimal.valueOf(existencia.getInvFinal()));
            dto.setTimestamp(existencia.getTimestamp());
            dto.setUsuario(existencia.getUsuario());

            // Si la relación con la entidad Producto/Sku existe, añadimos la descripción
            if (existencia.getSkuEntity() != null) {
                dto.setDescripcion(existencia.getSkuEntity().getDescripcion());
            }

            return dto;
        }).toList(); // Si usas Java 16+ puedes dejar .toList(), si usas Java 11 o inferior cambia a collect(Collectors.toList())
    }
}
