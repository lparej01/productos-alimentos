package com.sistemas.productos_alimentos.repository;

import com.sistemas.productos_alimentos.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    // Método automático para listar solo las categorías que estén activas (activo = 1)
    List<Categoria> findByActivo(Integer activo);

    // Método automático para buscar una categoría exacta por su nombre
    List<Categoria> findByCategoriaContainingIgnoreCase(String nombre);
}

