package com.sistemas.productos_alimentos.service.impl;


import com.sistemas.productos_alimentos.models.Producto;
import com.sistemas.productos_alimentos.models.ProductoId;
import com.sistemas.productos_alimentos.repository.ProductoRepository;
import com.sistemas.productos_alimentos.service.ProductoService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto buscarPorId(ProductoId id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Producto> buscarPorPeriodo(Integer periodo) {
        return productoRepository.findById_Periodo(periodo);
    }

    @Override
    public List<Producto> buscarPorCombo(Integer comboId) {
        return productoRepository.findById_ComboId(comboId);
    }

    @Override
    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto editar(ProductoId id, Producto detalles) {
        Optional<Producto> opcion = productoRepository.findById(id);
        if (opcion.isPresent()) {
            Producto existente = opcion.get();

            // Nota: La llave compuesta 'id' no se edita directamente, se editan sus valores métricos
            existente.setInvInicial(detalles.getInvInicial());
            existente.setEntradas(detalles.getEntradas());
            existente.setSalidas(detalles.getSalidas());
            existente.setMerma(detalles.getMerma());
            existente.setCostoUnitario(detalles.getCostoUnitario());
            existente.setPrecio(detalles.getPrecio());
            existente.setInvFinal(detalles.getInvFinal());
            existente.setTimestamp(detalles.getTimestamp());
            existente.setUsuario(detalles.getUsuario());

            return productoRepository.save(existente);
        }
        return null;
    }

    @Override
    public boolean eliminar(ProductoId id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
