package com.sistemas.productos_alimentos.models;

import com.fasterxml.jackson.annotation.JsonIgnore; // 🌟 IMPORTANTE
import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.Data;

@Entity
@Table(name = "existencia")
@Data
public class Existencia {

    @EmbeddedId
    // 🌟 SINCRONIZACION DE LLAVE COMPUESTA CON SQLITE:
    @AttributeOverrides({
            @AttributeOverride(name = "sku", column = @Column(name = "sku")),
            @AttributeOverride(name = "periodo", column = @Column(name = "periodo"))
    })
    private ExistenciaId id;



    @JsonIgnore // 🌟 Bloquea desbordamientos en la respuesta JSON
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sku", insertable = false, updatable = false)
    private Sku skuEntity;

    @JsonIgnore // 🌟 Bloquea desbordamientos en la respuesta JSON
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "periodo", insertable = false, updatable = false)
    private Periodo periodoEntity;

    @Column(name = "invinicial", nullable = false)
    private Double invInicial;

    @Column(name = "entradas", nullable = false)
    private Double entradas;

    @Column(name = "salidas", nullable = false)
    private Double salidas;

    @Column(name = "merma", nullable = false)
    private Double merma;

    @Column(name = "costounitario", precision = 5, scale = 2, nullable = false)
    private BigDecimal costoUnitario;

    @Column(name = "invfinal", nullable = false)
    private Double invFinal;

    @Column(name = "timestamp", length = 25, nullable = false)
    private String timestamp;

    @Column(name = "usuario", length = 20, nullable = false)
    private String usuario;


}


