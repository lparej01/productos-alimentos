package com.sistemas.productos_alimentos.repository;

import com.sistemas.productos_alimentos.models.MovInv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MovInvRepository extends JpaRepository<MovInv, Integer> {

    // Listar movimientos activos (activo = 1)
    List<MovInv> findByActivo(Integer activo);

    // Filtrar movimientos por tipo (ej. "ENTRADA", "SALIDA")
    List<MovInv> findByTipoMovInv(String tipoMovInv);

    // Filtrar movimientos por una fecha específica
    List<MovInv> findByFechaMovInv(String fechaMovInv);

    // Encontrar todos los movimientos vinculados a un pedido
    List<MovInv> findByPedidos_PedidoId(Integer pedidoId);
}

