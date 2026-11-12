package com.sistemas.productos_alimentos.repository;

import com.sistemas.productos_alimentos.models.Calendario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarioRepository extends JpaRepository<Calendario, String> { // Id es String

    // Buscar todos los días de un año específico
    List<Calendario> findByAnio(Integer anio);

    // Buscar los días de un mes y año concretos
    List<Calendario> findByAnioAndMes(Integer anio, Integer mes);

    // Listar todos los días feriados
    List<Calendario> findByEsFeriado(Integer esFeriado);

    // Listar fines de semana
    List<Calendario> findByEsWeekend(Integer esWeekend);
}

