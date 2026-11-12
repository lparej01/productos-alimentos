package com.sistemas.productos_alimentos.service;

import com.sistemas.productos_alimentos.models.Producto;
import com.sistemas.productos_alimentos.models.ProductoId;
import java.util.List;

public interface ProductoService {
    List<Producto> obtenerTodos();
    Producto buscarPorId(ProductoId id);
    List<Producto> buscarPorPeriodo(Integer periodo);
    List<Producto> buscarPorCombo(Integer comboId);
    Producto guardar(Producto producto);
    Producto editar(ProductoId id, Producto producto);
    boolean eliminar(ProductoId id);
}

