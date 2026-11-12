package com.sistemas.productos_alimentos.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "usuarios") // Hibernate se encargará de ponerle comillas gracias al yaml
public class Usuario {

    @Id
    @Column(name = "userid") // Coincide con tu minúscula de la tabla
    private String userId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private String role;

    @Column(name = "uuidv4")
    private String uuidv4;

    @Column(name = "active")
    private Integer active;

    @Column(name = "changePassword")
    private Integer changePassword;

    @Column(name = "nextPasswordUpdate")
    private String nextPasswordUpdate;

    @Column(name = "google")
    private Integer google;


    // Genera aquí tus Getters y Setters correspondientes
}


