package com.sistemas.productos_alimentos.service;

import com.sistemas.productos_alimentos.dto.ExistenciaResponseDTO;
import com.sistemas.productos_alimentos.models.Existencia;
import com.sistemas.productos_alimentos.models.ExistenciaId;
import com.sistemas.productos_alimentos.models.Periodo;

import java.util.List;

public interface ExistenciaService {
   // List<Existencia> obtenerTodas();
    Existencia buscarPorId(ExistenciaId id);
    List<Existencia> buscarPorPeriodo(Integer periodo);
    List<Existencia> buscarPorSku(Integer sku);
    Existencia guardar(Existencia existencia);
    Existencia editar(ExistenciaId id, Existencia existencia);
    boolean eliminar(ExistenciaId id);
    List<ExistenciaResponseDTO> obtenerTodas();
    List<ExistenciaResponseDTO> obtenerExistenciasDelPeriodo(Integer periodoActivo);
}
