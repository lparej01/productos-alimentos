package com.sistemas.productos_alimentos.repository;

import com.sistemas.productos_alimentos.models.Producto;
import com.sistemas.productos_alimentos.models.ProductoId; // Tu clase @Embeddable
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, ProductoId> {

    // Buscar inventarios por un periodo específico (columna dentro de la llave compuesta)
    List<Producto> findById_Periodo(Integer periodo);

    // Buscar el historial de un combo específico (columna dentro de la llave compuesta)
    List<Producto> findById_ComboId(Integer comboId);
}

