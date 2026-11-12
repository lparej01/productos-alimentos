package com.sistemas.productos_alimentos.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "calendario")
@Data
public class Calendario {

    @Id
    @Column(name = "calid", length = 10) // Físicamente en minúsculas
    private String calId;

    @Column(name = "periodo")
    private Integer periodo;

    @Column(name = "dia")
    private Integer dia;

    @Column(name = "mes")
    private Integer mes;

    @Column(name = "anio")
    private Integer anio;

    @Column(name = "diasem", length = 9) // Físicamente en minúsculas
    private String diaSem;

    @Column(name = "diasemnum") // Físicamente en minúsculas
    private Integer diaSemNum;

    @Column(name = "semiso") // Físicamente en minúsculas
    private Integer semIso;

    @Column(name = "nombremes", length = 10) // Físicamente en minúsculas
    private String nombreMes;

    @Column(name = "esweekend") // Físicamente en minúsculas
    private Integer esWeekend;

    @Column(name = "esferiado") // Físicamente en minúsculas
    private Integer esFeriado;
}
