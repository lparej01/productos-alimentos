package com.sistemas.productos_alimentos.service;

import com.sistemas.productos_alimentos.models.Categoria;
import java.util.List;

public interface CategoriaService {
    List<Categoria> obtenerTodas();
    List<Categoria> obtenerActivas();
    Categoria buscarPorId(Integer id); // Cambiado a Integer
    Categoria guardar(Categoria categoria);
    Categoria editar(Integer id, Categoria categoria); // Cambiado a Integer
    boolean eliminar(Integer id); // Cambiado a Integer

    List<Categoria> buscarPorNombre(String nombre);
}


