package com.campusmarket.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;



@Data
@AllArgsConstructor
public class ChatDTO {


    private Long id;

    private Long usuarioId;

    private String nombreUsuario;

    private LocalDateTime fechaCreacion;


}