package com.campusmarket.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
public class MensajeDTO {


    private Long id;

    private Long remitente;

    private String nombreRemitente;

    private String mensaje;

    private LocalDateTime fecha;


}