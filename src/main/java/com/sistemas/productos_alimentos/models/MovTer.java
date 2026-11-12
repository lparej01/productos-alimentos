package com.sistemas.productos_alimentos.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "movter")
@Data
public class MovTer {

    @Id
    @Column(name = "movterid")
    private Integer movTerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comboid", referencedColumnName = "comboid")
    private Combo combo;

    @Column(name = "tipomovter", length = 15, nullable = false)
    private String tipoMovTer;

    @Column(name = "fechamovter", length = 10, nullable = false)
    private String fechaMovTer;

    @Column(name = "cant", nullable = false)
    private Integer cant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planid", referencedColumnName = "planid", nullable = false)
    private Plan plan;

    @Column(name = "referencia", length = 50)
    private String referencia;

    @Column(name = "activo", nullable = false)
    private Integer activo;

    @Column(name = "timestamp", length = 25, nullable = false)
    private String timestamp;

    @Column(name = "usuario", length = 20, nullable = false)
    private String usuario;
}

