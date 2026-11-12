package com.sistemas.productos_alimentos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "combosxcat")
@Data
public class CombosxCat {

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "catId", column = @Column(name = "catId")),
            @AttributeOverride(name = "comboId", column = @Column(name = "comboId"))
    })
    private CombosxCatId id;
    @JsonIgnore // 🌟 FUERZA A SPRING A NO ENTRAR EN BUCLE CON LA CATEGORÍA
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    // 🌟 OPTIMIZACIÓN: Dejamos solo 'name' apuntando al campo físico de tu tabla intermedia.
    // 🌟 Al quitar 'referencedColumnName', Hibernate busca la PK de Categoria automáticamente.
    @JoinColumn(name = "catId", insertable = false, updatable = false)
    private Categoria categoria;

    @JsonIgnore // 🌟 FUERZA A SPRING A NO ENTRAR EN BUCLE CON LA CATEGORÍA
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    // 🌟 OPTIMIZACIÓN: Lo mismo para el combo.
    @JoinColumn(name = "comboId", insertable = false, updatable = false)
    private Combo combo;

    @Column(name = "cantUM", nullable = false)
    private Integer cantUM;

    @Column(name = "activo", nullable = false)
    private Integer activo;

    @Column(name = "timestamp", length = 25, nullable = false)
    private String timestamp;

    @Column(name = "usuario", length = 20, nullable = false)
    private String usuario;
}


