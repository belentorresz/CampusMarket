package com.campusmarket.dto;

import lombok.Data;

@Data
public class CompraDTO {

    private Long productoId;

    private Long compradorId;

    private String metodoPago;

    private String numeroPago;

    private String referenciaPago;

}