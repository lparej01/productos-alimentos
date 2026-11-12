package com.sistemas.productos_alimentos.service;

import com.sistemas.productos_alimentos.models.MovInv;
import java.util.List;

public interface MovInvService {
    List<MovInv> obtenerTodos();
    List<MovInv> obtenerActivos();
    MovInv buscarPorId(Integer movInvId);
    List<MovInv> buscarPorTipo(String tipoMovInv);
    List<MovInv> buscarPorFecha(String fechaMovInv);
    List<MovInv> buscarPorPedido(Integer pedidoId);
    MovInv guardar(MovInv movInv);
    MovInv editar(Integer movInvId, MovInv movInv);
    boolean eliminar(Integer movInvId);
}

