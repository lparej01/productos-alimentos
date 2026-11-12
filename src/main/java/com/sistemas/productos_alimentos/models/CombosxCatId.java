package com.sistemas.productos_alimentos.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data                       // 🌟 Genera getters, setters, equals() y hashCode() automáticamente
@NoArgsConstructor          // 🌟 Constructor vacío obligatorio para Hibernate
@AllArgsConstructor         // 🌟 Constructor con parámetros para tus controladores
public class CombosxCatId implements Serializable { // 🌟 Obligatorio implementar Serializable

    @Column(name = "catid")
    private Integer catId;

    @Column(name = "comboid")
    private Integer comboId;
}

