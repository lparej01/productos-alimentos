package com.sistemas.productos_alimentos.service;

import com.sistemas.productos_alimentos.models.MovTer;
import java.util.List;

public interface MovTerService {
    List<MovTer> obtenerTodos();
    List<MovTer> obtenerActivos();
    MovTer buscarPorId(Integer movTerId);
    List<MovTer> buscarPorTipo(String tipoMovTer);
    List<MovTer> buscarPorFecha(String fechaMovTer);
    List<MovTer> buscarPorPlan(Integer planId);
    MovTer guardar(MovTer movTer);
    MovTer editar(Integer movTerId, MovTer movTer);
    boolean eliminar(Integer movTerId);


}

