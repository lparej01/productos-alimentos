package com.sistemas.productos_alimentos.models;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;


@Entity
@Table(name = "combos")
@Data
public class Combo {

    @Id
    @Column(name = "comboid")
    private Integer comboId;

    @Column(name = "desccombo", length = 30, nullable = false)
    private String descCombo;

    @Column(name = "peso", precision = 5, scale = 2, nullable = false)
    private BigDecimal peso;

    @Column(name = "activo", nullable = false)
    private Integer activo;

    @Column(name = "timestamp", length = 25, nullable = false)
    private String timestamp;

    @Column(name = "usuario", length = 20, nullable = false)
    private String usuario;
}

