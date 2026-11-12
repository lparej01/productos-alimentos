package com.sistemas.productos_alimentos.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.Data;

@Entity
@Table(name = "pedidos")
@Data
public class Pedidos {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedidoid") // Físicamente en minúscula
    private Integer pedidoId;

    @Column(name = "fechapedido", length = 10, nullable = false)
    private String fechaPedido;

    // Relación con Proveedor (provid)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "provid", nullable = false)
    private Proveedores proveedores;

    // Relación con Sku (sku)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sku")
    private Sku sku;

    @Column(name = "cant", nullable = false)
    private Integer cant;

    @Column(name = "costounitario", nullable = false)
    private BigDecimal costoUnitario;

    @Column(name = "costototal", nullable = false)
    private BigDecimal costoTotal;

    @Column(name = "flete", nullable = false)
    private BigDecimal flete;

    @Column(name = "costototalflete", nullable = false)
    private BigDecimal costoTotalFlete;

    @Column(name = "cantpendiente")
    private Integer cantPendiente;

    @Column(name = "saldopendiente")
    private BigDecimal saldoPendiente;

    @Column(name = "activo", nullable = false)
    private Integer activo;

    @Column(name = "timestamp", length = 25, nullable = false)
    private String timestamp;

    @Column(name = "usuario", length = 20, nullable = false)
    private String usuario;

    public Pedidos() {
    }

    /**
     * Asegura los valores por defecto históricos de Laravel antes de insertar,
     * previniendo conflictos con restricciones NOT NULL de SQLite.
     */
    @PrePersist
    protected void onCreate() {
        if (this.cant == null) this.cant = 0;
        if (this.costoUnitario == null) this.costoUnitario = BigDecimal.ZERO;
        if (this.costoTotal == null) this.costoTotal = BigDecimal.ZERO;
        if (this.flete == null) this.flete = BigDecimal.ZERO;
        if (this.costoTotalFlete == null) this.costoTotalFlete = BigDecimal.ZERO;
        if (this.cantPendiente == null) this.cantPendiente = 0;
        if (this.saldoPendiente == null) this.saldoPendiente = BigDecimal.ZERO;
        if (this.activo == null) this.activo = 1;
        if (this.timestamp == null) this.timestamp = "20000101000000";
        if (this.usuario == null) this.usuario = "MASTER_USER";
    }

    // Generar Getters y Setters manteniendo CamelCase en Java
}
