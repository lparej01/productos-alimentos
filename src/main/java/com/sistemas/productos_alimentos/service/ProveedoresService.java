package com.sistemas.productos_alimentos.service;


import com.sistemas.productos_alimentos.models.Proveedores;
import java.util.List;

public interface ProveedoresService {
    List<Proveedores> obtenerTodos();
    List<Proveedores> obtenerActivos();
    Proveedores buscarPorId(Integer provId);
    List<Proveedores> buscarPorNombre(String nombre);
    Proveedores guardar(Proveedores proveedor);
    Proveedores editar(Integer provId, Proveedores proveedor);
    boolean eliminar(Integer provId);
}

