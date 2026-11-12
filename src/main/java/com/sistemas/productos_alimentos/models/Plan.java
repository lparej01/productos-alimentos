package com.sistemas.productos_alimentos.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "planes")
@Data
public class Plan {

    @Id
    @Column(name = "planid")
    private Integer planId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comboplanid", referencedColumnName = "comboid")
    private Combo comboPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comboprodid", referencedColumnName = "comboid")
    private Combo comboProd;

    @Column(name = "tipoplan", length = 10, nullable = false)
    private String tipoPlan;

    @Column(name = "fechaplan", length = 10)
    private String fechaPlan;

    @Column(name = "fechaprod", length = 10)
    private String fechaProd;

    @Column(name = "cantcombosplan")
    private Integer cantCombosPlan;

    @Column(name = "cantcombosprod")
    private Integer cantCombosProd;

    @Column(name = "strequisicion", nullable = false)
    private Integer stRequisicion;

    @Column(name = "stproduccion", nullable = false)
    private Integer stProduccion;

    @Column(name = "merma")
    private Double merma;

    @Column(name = "activo", nullable = false)
    private Integer activo;

    @Column(name = "timestamp", length = 25, nullable = false)
    private String timestamp;

    @Column(name = "usuario", length = 20, nullable = false)
    private String usuario;
}
