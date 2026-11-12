package com.sistemas.productos_alimentos.service;


import com.sistemas.productos_alimentos.models.Plan;
import java.util.List;

public interface PlanService {
    List<Plan> obtenerTodos();
    List<Plan> obtenerActivos();
    Plan buscarPorId(Integer id);
    List<Plan> buscarPorTipo(String tipoPlan);
    List<Plan> buscarPorFechaPlan(String fechaPlan);
    List<Plan> buscarPorFechaProd(String fechaProd);
    Plan guardar(Plan plan);
    Plan editar(Integer id, Plan plan);
    boolean eliminar(Integer id);
}

