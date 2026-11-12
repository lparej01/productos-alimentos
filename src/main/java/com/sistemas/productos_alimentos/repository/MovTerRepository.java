package com.sistemas.productos_alimentos.repository;

import com.sistemas.productos_alimentos.models.MovTer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MovTerRepository extends JpaRepository<MovTer, Integer> {

    // Listar movimientos activos (activo = 1)
    List<MovTer> findByActivo(Integer activo);

    // Filtrar movimientos por tipo (ej. "ENTRADA", "SALIDA")
    List<MovTer> findByTipoMovTer(String tipoMovTer);

    // Filtrar por una fecha específica de movimiento terminado
    List<MovTer> findByFechaMovTer(String fechaMovTer);

    // Encontrar todos los movimientos de producto terminado ligados a un Plan de producción
    List<MovTer> findByPlan_PlanId(Integer planId);
}

