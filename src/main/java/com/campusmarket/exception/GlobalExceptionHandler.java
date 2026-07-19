package com.campusmarket.exception;


import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;



@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<ErrorResponse> manejarNoEncontrado(
            RecursoNoEncontradoException ex
    ){


        ErrorResponse error =
                new ErrorResponse(
                        404,
                        ex.getMessage()
                );


        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);

    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> manejarGeneral(
            Exception ex
    ){


        ErrorResponse error =
                new ErrorResponse(
                        500,
                        "Error interno del servidor"
                );


        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);

    }


}