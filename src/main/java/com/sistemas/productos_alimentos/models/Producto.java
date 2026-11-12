package com.sistemas.productos_alimentos.models;

import com.fasterxml.jackson.annotation.JsonIgnore; // 🌟 IMPORTANTE
import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.Data;

@Entity
@Table(name = "productos")
@Data
public class Producto {

    @EmbeddedId
    // 🌟 ENLAZA LAS VARIABLES CON LAS COLUMNAS REALES DE TU TABLA FÍSICA:
    @AttributeOverrides({
            @AttributeOverride(name = "comboId", column = @Column(name = "comboid")),
            @AttributeOverride(name = "periodo", column = @Column(name = "periodo"))
    })
    private ProductoId id;

    @JsonIgnore // 🌟 Evita el error 500 al transformar a JSON
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comboid", insertable = false, updatable = false)
    private Combo combo;

    @JsonIgnore // 🌟 Evita el error 500 con los proxys de Hibernate
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "periodo", insertable = false, updatable = false)
    private Periodo periodoEntity;

    @Column(name = "invinicial", nullable = false)
    private Double invInicial;

    @Column(name = "entradas", nullable = false)
    private Double entradas; // O entradas según tu mapeo original

    @Column(name = "salidas", nullable = false)
    private Double salidas;

    @Column(name = "merma", nullable = false)
    private Double merma;

    @Column(name = "costounitario", precision = 5, scale = 2, nullable = false)
    private BigDecimal costoUnitario;

    @Column(name = "precio", precision = 5, scale = 2, nullable = false)
    private BigDecimal precio;

    @Column(name = "invfinal", nullable = false)
    private Double invFinal;

    @Column(name = "timestamp", length = 25, nullable = false)
    private String timestamp;

    @Column(name = "usuario", length = 20, nullable = false)
    private String usuario;
}


