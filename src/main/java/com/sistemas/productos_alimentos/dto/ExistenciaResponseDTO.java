package com.sistemas.productos_alimentos.dto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ExistenciaResponseDTO {
    // Getters obligatorios para que Jackson genere el JSON hacia Angular
    private Long sku;
    private String descripcion;
    private Integer periodo;
    private BigDecimal invInicial;
    private BigDecimal entradas;
    private BigDecimal salidas;
    private BigDecimal merma;
    private BigDecimal costoUnitario;
    private BigDecimal invFinal;
    private String timestamp;
    private String usuario;

    // Constructor que procesa el Object[] crudo de SQLite de forma manual y segura
    public ExistenciaResponseDTO() {
        this.sku = 0L;
        this.descripcion = "Sin descripcion";
        this.periodo = 0;
        this.invInicial = BigDecimal.valueOf(0.0); // Cambiar a BigDecimal.ZERO si tus atributos del DTO son BigDecimal
        this.entradas = BigDecimal.valueOf(0.0);
        this.salidas = BigDecimal.valueOf(0.0);
        this.merma = BigDecimal.valueOf(0.0);
        this.costoUnitario = java.math.BigDecimal.ZERO;
        this.invFinal = BigDecimal.valueOf(0.0);
        this.timestamp = "";
        this.usuario = "SYSTEM";
        return;
    }

}

