package com.campusmarket.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String nombre;


    @Column(nullable = false, unique = true)
    private String correo;


    @Column(nullable = false, unique = true)
    private String dni;


    @Column(nullable = false, unique = true)
    private String codigo;


    private String facultad;


    @Column(nullable = false)
    private String password;


    private String foto;

    private String numeroCelular;


    private LocalDateTime fechaRegistro = LocalDateTime.now();


    @Enumerated(EnumType.STRING)
    private Rol rol = Rol.USUARIO;

    private String codigoRecuperacion;

    private LocalDateTime expiracionCodigo;

}