package com.sistemas.productos_alimentos.repository;

import com.sistemas.productos_alimentos.models.Proveedores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProveedoresRepository extends JpaRepository<Proveedores, Integer> {

    // Listar proveedores activos (activo = 1)
    List<Proveedores> findByActivo(Integer activo);

    // Buscar proveedores por nombre (ignora mayúsculas/minúsculas)
    List<Proveedores> findByNombreContainingIgnoreCase(String nombre);
}

