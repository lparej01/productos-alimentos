package com.sistemas.productos_alimentos.service;


import com.sistemas.productos_alimentos.models.Periodo;
import java.util.List;

public interface PeriodoService {
    List<Periodo> obtenerTodos();
    Periodo buscarPorId(Integer idPeriodo);
    List<Periodo> buscarPorAnio(Integer anio);
    Periodo obtenerPeriodoActual();
    List<Periodo> obtenerPeriodosAbiertos();
    Periodo guardar(Periodo periodo);
    Periodo editar(Integer idPeriodo, Periodo periodo);
    boolean eliminar(Integer idPeriodo);
    void aperturarSiguientePeriodo(String usuarioAnfitrion);
    Integer calcularSiguientePeriodo(Integer periodoActual);
}

