package com.sistemas.productos_alimentos.service.impl;

import com.sistemas.productos_alimentos.models.Pedidos;
import com.sistemas.productos_alimentos.repository.PedidosRepository;
import com.sistemas.productos_alimentos.service.PedidosService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PedidosServiceImpl implements PedidosService {

    private final PedidosRepository pedidosRepository;

    public PedidosServiceImpl(PedidosRepository pedidosRepository) {
        this.pedidosRepository = pedidosRepository;
    }

    @Override
    public List<Pedidos> obtenerTodos() {
        return pedidosRepository.findAllOptimizado();
    }

    @Override
    public List<Pedidos> obtenerActivos() {
        return pedidosRepository.findByActivo(1);
    }

    @Override
    public Pedidos buscarPorId(Integer id) {
        return pedidosRepository.findById(id).orElse(null);
    }

    @Override
    public List<Pedidos> buscarPorFecha(String fecha) {
        return pedidosRepository.findByFechaPedido(fecha);
    }

    @Override
    public List<Pedidos> buscarPorProveedor(Integer provId) {
        return pedidosRepository.findByProveedores_ProvId(provId);
    }

    @Override
    public Pedidos guardar(Pedidos pedido) {
        return pedidosRepository.save(pedido);
    }

    @Override
    public Pedidos editar(Integer id, Pedidos detalles) {
        Optional<Pedidos> opcion = pedidosRepository.findById(id);
        if (opcion.isPresent()) {
            Pedidos existente = opcion.get();

            existente.setFechaPedido(detalles.getFechaPedido());
            existente.setProveedores(detalles.getProveedores());
            existente.setSku(detalles.getSku());
            existente.setCant(detalles.getCant());
            existente.setCostoUnitario(detalles.getCostoUnitario());
            existente.setCostoTotal(detalles.getCostoTotal());
            existente.setFlete(detalles.getFlete());
            existente.setCostoTotalFlete(detalles.getCostoTotalFlete());
            existente.setCantPendiente(detalles.getCantPendiente());
            existente.setSaldoPendiente(detalles.getSaldoPendiente());
            existente.setActivo(detalles.getActivo());
            existente.setTimestamp(detalles.getTimestamp());
            existente.setUsuario(detalles.getUsuario());

            return pedidosRepository.save(existente);
        }
        return null;
    }

    @Override
    public boolean eliminar(Integer id) {
        if (pedidosRepository.existsById(id)) {
            pedidosRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
