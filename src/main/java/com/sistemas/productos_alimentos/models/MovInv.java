package com.sistemas.productos_alimentos.models;



import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "movinv")
@Data
public class MovInv {

    @Id
    @Column(name = "movinvid")
    private Integer movInvId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sku", referencedColumnName = "sku")
    private Sku sku;

    @Column(name = "tipomovinv", length = 15, nullable = false)
    private String tipoMovInv;

    @Column(name = "fechamovinv", length = 10, nullable = false)
    private String fechaMovInv;

    @Column(name = "cant", nullable = false)
    private Integer cant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedidoid", referencedColumnName = "pedidoid", nullable = false)
    private Pedidos pedidos;

    @Column(name = "referencia", length = 50)
    private String referencia;

    @Column(name = "activo", nullable = false)
    private Integer activo;

    @Column(name = "timestamp", length = 25, nullable = false)
    private String timestamp;

    @Column(name = "usuario", length = 20, nullable = false)
    private String usuario;
}

