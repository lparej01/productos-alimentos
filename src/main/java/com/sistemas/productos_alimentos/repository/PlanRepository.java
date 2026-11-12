package com.sistemas.productos_alimentos.repository;


import com.sistemas.productos_alimentos.models.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {

    // Listar planes activos (activo = 1)
    List<Plan> findByActivo(Integer activo);

    // Buscar por tipo de plan (ej. "Semanal", "Mensual")
    List<Plan> findByTipoPlan(String tipoPlan);

    // Buscar planes por su fecha de planificación
    List<Plan> findByFechaPlan(String fechaPlan);

    // Buscar planes por su fecha de producción
    List<Plan> findByFechaProd(String fechaProd);
}


