package com.sistemas.productos_alimentos.service;


import com.sistemas.productos_alimentos.models.Calendario;
import java.util.List;

public interface CalendarioService {
    List<Calendario> obtenerTodos();
    Calendario buscarPorId(String id);
    List<Calendario> buscarPorAnio(Integer anio);
    List<Calendario> buscarPorMesYAnio(Integer anio, Integer mes);
    List<Calendario> obtenerFeriados();
    List<Calendario> obtenerFinesDeSemana();
    Calendario guardar(Calendario calendario);
    Calendario editar(String id, Calendario calendario);
    boolean eliminar(String id);
}
