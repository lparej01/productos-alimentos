package com.sistemas.productos_alimentos.service;

import com.sistemas.productos_alimentos.models.Sku;
import java.util.List;


public interface SkuService {
    List<Sku> obtenerTodos();
    List<Sku> obtenerActivos();
    Sku buscarPorId(Integer skuId);
    List<Sku> buscarPorDescripcion(String descripcion);
    List<Sku> buscarPorCategoria(Integer catId);
    Sku guardar(Sku sku);
    Sku editar(Integer skuId, Sku sku);
    boolean eliminar(Integer skuId);
}


