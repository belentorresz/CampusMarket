package com.campusmarket.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class ProductoDTO {


    private Long id;


    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;


    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;


    @NotNull(message = "El precio es obligatorio")
    @PositiveOrZero(message = "El precio no puede ser negativo")    private BigDecimal precio;


    private String estado;

    private String imagen;


    @NotBlank(message = "La condición es obligatoria")
    private String condicion;


    @NotNull(message = "Debe seleccionar un usuario")
    private Long usuarioId;


    @NotNull(message = "Debe seleccionar una categoría")
    private Long categoriaId;


    // NUEVO
    private UsuarioDTO usuario;


}