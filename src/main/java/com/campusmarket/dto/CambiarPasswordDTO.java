package com.campusmarket.dto;

import lombok.Data;

@Data
public class CambiarPasswordDTO {

    private Long usuarioId;

    private String passwordActual;

    private String nuevaPassword;

}