package com.campusmarket.exception;


import java.time.LocalDateTime;


public class ErrorResponse {


    private LocalDateTime fecha;

    private int codigo;

    private String mensaje;



    public ErrorResponse(
            int codigo,
            String mensaje
    ){

        this.fecha = LocalDateTime.now();
        this.codigo = codigo;
        this.mensaje = mensaje;

    }



    public LocalDateTime getFecha(){

        return fecha;

    }


    public int getCodigo(){

        return codigo;

    }


    public String getMensaje(){

        return mensaje;

    }

}