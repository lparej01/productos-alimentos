package com.sistemas.productos_alimentos.repository;

import com.sistemas.productos_alimentos.models.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidosRepository extends JpaRepository<Pedidos, Integer> {

    // Listar pedidos activos (activo = 1)
    List<Pedidos> findByActivo(Integer activo);

    // Buscar pedidos por una fecha específica
    List<Pedidos> findByFechaPedido(String fechaPedido);

    // Buscar pedidos asociados a un proveedor específico por su ID
    List<Pedidos> findByProveedores_ProvId(Integer provId);

    // JOIN FETCH fuerza a Hibernate a traer las entidades relacionadas de un solo viaje
    @Query("SELECT DISTINCT p FROM Pedidos p " +
            "LEFT JOIN FETCH p.proveedores " +
            "LEFT JOIN FETCH p.sku " +
            "ORDER BY p.pedidoId DESC")
    List<Pedidos> findAllOptimizado();
}

