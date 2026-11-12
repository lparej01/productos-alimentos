package com.sistemas.productos_alimentos.models;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "periodo")
@Data
public class Periodo {

    @Id
    @Column(name = "periodo") // Todo en minúsculas
    private Integer idPeriodo; // Renombrado sutilmente para evitar confusión con el objeto, o puedes usar 'periodo'

    @Column(name = "anio", nullable = false)
    private Integer anio;

    @Column(name = "mes", nullable = false)
    private Integer mes;

    @Column(name = "mesnombre", length = 10, nullable = false) // Todo en minúsculas
    private String mesNombre;

    @Column(name = "escerrado", nullable = false) // Todo en minúsculas
    private Integer esCerrado;

    @Column(name = "esactual", nullable = false) // Todo en minúsculas
    private Integer esActual;

    @Column(name = "timestamp", length = 25, nullable = false)
    private String timestamp;

    @Column(name = "usuario", length = 20, nullable = false)
    private String usuario;
}
