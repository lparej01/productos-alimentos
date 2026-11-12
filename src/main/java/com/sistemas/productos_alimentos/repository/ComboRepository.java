package com.sistemas.productos_alimentos.repository;

import com.sistemas.productos_alimentos.models.Combo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComboRepository extends JpaRepository<Combo, Integer> {

    // Listar combos activos (activo = 1)
    List<Combo> findByActivo(Integer activo);

    // Buscar combos por su descripción (LIKE %texto%) e ignorando mayúsculas
    List<Combo> findByDescComboContainingIgnoreCase(String descCombo);
}


