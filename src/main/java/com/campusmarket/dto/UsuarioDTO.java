package com.campusmarket.dto;


import com.campusmarket.entity.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {


    private Long id;

    private String nombre;

    private String correo;

    private Rol rol;

    private String dni;

    private String codigo;

    private String facultad;

    private String foto;

    private String numeroCelular;

}