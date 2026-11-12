package com.sistemas.productos_alimentos.service;


import com.sistemas.productos_alimentos.models.CombosxCat;
import com.sistemas.productos_alimentos.models.CombosxCatId;
import java.util.List;

public interface CombosxCatService {
    List<CombosxCat> obtenerTodos();
    List<CombosxCat> obtenerActivos();
    CombosxCat buscarPorId(CombosxCatId id);
    List<CombosxCat> buscarPorCategoria(Integer catId);
    List<CombosxCat> buscarPorCombo(Integer comboId);
    CombosxCat guardar(CombosxCat combosxCat);
    CombosxCat editar(CombosxCatId id, CombosxCat combosxCat);
    boolean eliminar(CombosxCatId id);
}

