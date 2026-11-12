package com.sistemas.productos_alimentos.models;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "sku")
@Data
public class Sku {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sku") // El nombre del campo id principal coincide en minúsculas
    private Integer sku;

    // Relación de muchos SKU pertenecen a una Categoría
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "catId", nullable = false) // Mapea la FK 'catid' en minúsculas
    private Categoria categoria;

    @Column(name = "marca", length = 50, nullable = false)
    private String marca;

    @Column(name = "descripcion", length = 80, nullable = false)
    private String descripcion;

    @Column(name = "presentacion", nullable = false)
    private Double presentacion;

    @Column(name = "unidadmedida", length = 2, nullable = false) // Todo en minúsculas físicos
    private String unidadMedida;

    @Column(name = "empaque", nullable = false)
    private Integer empaque;

    @Column(name = "costounitario", precision = 5, scale = 2, nullable = false) // Todo en minúsculas físicos
    private BigDecimal costoUnitario;

    @Column(name = "activo", nullable = false)
    private Integer activo;

    @Column(name = "timestamp", length = 25, nullable = false)
    private String timestamp;

    @Column(name = "usuario", length = 20, nullable = false)
    private String usuario;
}
