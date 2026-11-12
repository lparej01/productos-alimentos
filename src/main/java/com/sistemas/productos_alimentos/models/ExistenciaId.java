package com.sistemas.productos_alimentos.models;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
@Data
public class ExistenciaId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "sku")
    private Integer sku;

    @Column(name = "periodo")
    private Integer periodo;
}

