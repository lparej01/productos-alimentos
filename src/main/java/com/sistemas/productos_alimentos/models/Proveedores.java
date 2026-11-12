package com.sistemas.productos_alimentos.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "proveedores")
@Data
public class Proveedores {

    @Id
    @Column(name = "provid")
    private Integer provId;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "contacto", length = 50)
    private String contacto;

    @Column(name = "telf1", length = 15)
    private String telf1;

    @Column(name = "telf2", length = 15)
    private String telf2;

    @Column(name = "telfcontacto", length = 15)
    private String telfContacto;

    @Column(name = "email", length = 40)
    private String email;

    @Column(name = "emailcontacto", length = 40)
    private String emailContacto;

    @Column(name = "activo", nullable = false)
    private Integer activo;

    @Column(name = "timestamp", length = 25, nullable = false)
    private String timestamp;

    @Column(name = "usuario", length = 20)
    private String usuario;
}

