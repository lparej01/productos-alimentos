package com.sistemas.productos_alimentos.service;

import com.sistemas.productos_alimentos.models.Pedidos;
import java.util.List;

public interface PedidosService {
    List<Pedidos> obtenerTodos();
    List<Pedidos> obtenerActivos();
    Pedidos buscarPorId(Integer id);
    List<Pedidos> buscarPorFecha(String fecha);
    List<Pedidos> buscarPorProveedor(Integer provId);
    Pedidos guardar(Pedidos pedido);
    Pedidos editar(Integer id, Pedidos pedido);
    boolean eliminar(Integer id);
}
