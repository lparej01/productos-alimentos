package com.sistemas.productos_alimentos.repository;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Value;

public interface ExistenciaConDescripcion {

    @Value("#{target.sku}")
    Long getSku();

    @Value("#{target.descripcion}")
    String getDescripcion(); // <-- Esta anotación obliga a Spring a buscar la columna 'descripcion'

    @Value("#{target.periodo}")
    Integer getPeriodo();

    @Value("#{target.invInicial}")
    BigDecimal getInvInicial();

    @Value("#{target.entradas}")
    BigDecimal getEntradas();

    @Value("#{target.salidas}")
    BigDecimal getSalidas();

    @Value("#{target.merma}")
    BigDecimal getMerma();

    @Value("#{target.costoUnitario}")
    BigDecimal getCostoUnitario();

    @Value("#{target.invFinal}")
    BigDecimal getInvFinal();

    @Value("#{target.timestamp}")
    String getTimestamp();

    @Value("#{target.usuario}")
    String getUsuario();
}
