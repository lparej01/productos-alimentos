package com.sistemas.productos_alimentos.service.impl;


import com.sistemas.productos_alimentos.models.MovInv;
import com.sistemas.productos_alimentos.repository.MovInvRepository;
import com.sistemas.productos_alimentos.service.MovInvService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MovInvServiceImpl implements MovInvService {

    private final MovInvRepository movInvRepository;

    public MovInvServiceImpl(MovInvRepository movInvRepository) {
        this.movInvRepository = movInvRepository;
    }

    @Override
    public List<MovInv> obtenerTodos() {
        return movInvRepository.findAll();
    }

    @Override
    public List<MovInv> obtenerActivos() {
        return movInvRepository.findByActivo(1);
    }

    @Override
    public MovInv buscarPorId(Integer movInvId) {
        return movInvRepository.findById(movInvId).orElse(null);
    }

    @Override
    public List<MovInv> buscarPorTipo(String tipoMovInv) {
        return movInvRepository.findByTipoMovInv(tipoMovInv);
    }

    @Override
    public List<MovInv> buscarPorFecha(String fechaMovInv) {
        return movInvRepository.findByFechaMovInv(fechaMovInv);
    }

    @Override
    public List<MovInv> buscarPorPedido(Integer pedidoId) {
        return movInvRepository.findByPedidos_PedidoId(pedidoId);
    }

    @Override
    public MovInv guardar(MovInv movInv) {
        return movInvRepository.save(movInv);
    }

    @Override
    public MovInv editar(Integer movInvId, MovInv detalles) {
        Optional<MovInv> opcion = movInvRepository.findById(movInvId);
        if (opcion.isPresent()) {
            MovInv existente = opcion.get();

            existente.setSku(detalles.getSku());
            existente.setTipoMovInv(detalles.getTipoMovInv());
            existente.setFechaMovInv(detalles.getFechaMovInv());
            existente.setCant(detalles.getCant());
            existente.setPedidos(detalles.getPedidos());
            existente.setReferencia(detalles.getReferencia());
            existente.setActivo(detalles.getActivo());
            existente.setTimestamp(detalles.getTimestamp());
            existente.setUsuario(detalles.getUsuario());

            return movInvRepository.save(existente);
        }
        return null;
    }

    @Override
    public boolean eliminar(Integer movInvId) {
        if (movInvRepository.existsById(movInvId)) {
            movInvRepository.deleteById(movInvId);
            return true;
        }
        return false;
    }
}
