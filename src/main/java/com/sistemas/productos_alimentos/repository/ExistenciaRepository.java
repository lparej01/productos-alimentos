package com.sistemas.productos_alimentos.repository;

import com.sistemas.productos_alimentos.models.Existencia;
import com.sistemas.productos_alimentos.models.ExistenciaId; // Tu clase @Embeddable
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface ExistenciaRepository extends JpaRepository<Existencia, ExistenciaId> {

    // Filtrar todo el inventario de existencias para un Periodo contable específico
    List<Existencia> findById_Periodo(Integer periodo);

    // Filtrar el historial de existencias de un SKU específico
    List<Existencia> findById_Sku(Integer sku);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO existencia (periodo, sku, invinicial, entradas, salidas, merma, costounitario, invfinal, timestamp, usuario) " +
            "SELECT :nuevoPeriodo, e.sku, e.invfinal, 0.0, 0.0, 0.0, e.costounitario, e.invfinal, :timestamp, :usuario " +
            "FROM existencia e " +
            "INNER JOIN sku s ON e.sku = s.sku " +
            "WHERE e.periodo = :periodoActual AND s.activo = 1", nativeQuery = true)
    int aperturarNuevoPeriodo(
            @Param("periodoActual") Integer periodoActual,
            @Param("nuevoPeriodo") Integer nuevoPeriodo,
            @Param("timestamp") String timestamp,
            @Param("usuario") String usuario );

    @Query(value = "SELECT " +
            "    s.sku, " +               // posicion 0
            "    s.descripcion, " +       // posicion 1
            "    COALESCE(e.periodo, 0), " + // posicion 2
            "    COALESCE(e.invInicial, 0), " + // posicion 3
            "    COALESCE(e.entradas, 0), " + // posicion 4
            "    COALESCE(e.salidas, 0), " + // posicion 5
            "    COALESCE(e.merma, 0), " + // posicion 6
            "    COALESCE(e.costoUnitario, 0), " + // posicion 7
            "    COALESCE(e.invFinal, 0), " + // posicion 8
            "    COALESCE(e.timestamp, CURRENT_TIMESTAMP), " + // posicion 9
            "    COALESCE(e.usuario, 'SYSTEM') " + // posicion 10
            "FROM sku s " +
            "LEFT JOIN existencia e ON s.sku = e.sku " +
            "WHERE s.activo = 1", nativeQuery = true)
    List<Object[]> obtenerTodasConDescripcionRaw();






}

