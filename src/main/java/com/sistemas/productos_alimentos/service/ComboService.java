package com.sistemas.productos_alimentos.service;

import com.sistemas.productos_alimentos.models.Combo;
import java.util.List;

public interface ComboService {
    List<Combo> obtenerTodos();
    List<Combo> obtenerActivos();
    Combo buscarPorId(Integer comboId);
    List<Combo> buscarPorDescripcion(String descCombo);
    Combo guardar(Combo combo);
    Combo editar(Integer comboId, Combo combo);
    boolean eliminar(Integer comboId);
}

