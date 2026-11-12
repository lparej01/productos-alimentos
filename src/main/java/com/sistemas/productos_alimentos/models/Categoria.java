package com.sistemas.productos_alimentos.models;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "categorias")
@Data
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 🚨 AGREGA ESTA LÍNEA
    @Column(name = "catId") //
    private Integer catId;

    @Column(name = "categoria", length = 50, nullable = false)
    private String categoria;

    @Column(name = "costounitario", precision = 5, scale = 2, nullable = false) // 🌟 CORRECCIÓN: Todo en minúsculas
    private BigDecimal costoUnitario;

    @Column(name = "precio", precision = 5, scale = 2, nullable = false)
    private BigDecimal precio;

    @Column(name = "peso", nullable = false)
    private Double peso;

    @Column(name = "activo", nullable = false)
    private Integer activo;

    @Column(name = "timestamp", length = 25, nullable = false)
    private String timestamp;

    @Column(name = "usuario", length = 20, nullable = false)
    private String usuario;
}


