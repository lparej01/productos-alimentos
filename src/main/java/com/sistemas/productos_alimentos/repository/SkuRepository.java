package com.sistemas.productos_alimentos.repository;

import com.sistemas.productos_alimentos.models.Sku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;



@Repository
public interface SkuRepository extends JpaRepository<Sku, Integer> {

    // Listar SKUs activos (activo = 1)
    List<Sku> findByActivo(Integer activo);

    // Buscar SKUs por descripción (ignora mayúsculas/minúsculas)
    List<Sku> findByDescripcionContainingIgnoreCase(String descripcion);

    // Filtrar todos los SKUs que pertenezcan a una categoría específica
    List<Sku> findByCategoria_CatId(Integer catId);


}


