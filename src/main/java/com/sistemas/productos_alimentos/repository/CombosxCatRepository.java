package com.sistemas.productos_alimentos.repository;

import com.sistemas.productos_alimentos.models.CombosxCat;
import com.sistemas.productos_alimentos.models.CombosxCatId; // Tu clase @Embeddable
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CombosxCatRepository extends JpaRepository<CombosxCat, CombosxCatId> {

    // Listar relaciones activas (activo = 1)
    List<CombosxCat> findByActivo(Integer activo);

    // Filtrar todas las asignaciones de una Categoría específica usando la FK interna del ID
    List<CombosxCat> findById_CatId(Integer catId);

    // Filtrar todas las asignaciones de un Combo específico usando la FK interna del ID
    List<CombosxCat> findById_ComboId(Integer comboId);
}


