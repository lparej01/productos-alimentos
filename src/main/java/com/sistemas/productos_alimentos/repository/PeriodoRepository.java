package com.sistemas.productos_alimentos.repository;

import com.sistemas.productos_alimentos.models.Periodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PeriodoRepository extends JpaRepository<Periodo, Integer> {

    // Buscar todos los periodos de un año específico
    List<Periodo> findByAnio(Integer anio);

    // Encontrar el periodo operativo actual
    Optional<Periodo> findByEsActual(Integer esActual);





    // Listar periodos según su estado de cierre (Cerrado = 1 o 0)
    List<Periodo> findByEsCerrado(Integer esCerrado);


}


