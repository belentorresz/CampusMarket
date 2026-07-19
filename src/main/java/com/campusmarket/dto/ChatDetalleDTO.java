package com.campusmarket.dto;


import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ChatDetalleDTO {


    private Long id;


    private Long usuario1;


    private String nombreUsuario1;


    private Long usuario2;


    private String nombreUsuario2;


    private LocalDateTime fechaCreacion;



    public ChatDetalleDTO(
            Long id,
            Long usuario1,
            String nombreUsuario1,
            Long usuario2,
            String nombreUsuario2,
            LocalDateTime fechaCreacion
    ){

        this.id=id;
        this.usuario1=usuario1;
        this.nombreUsuario1=nombreUsuario1;
        this.usuario2=usuario2;
        this.nombreUsuario2=nombreUsuario2;
        this.fechaCreacion=fechaCreacion;

    }

}