package com.campusmarket.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
public class RegistroDTO {


    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;


    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo no es válido")
    private String correo;


    @NotBlank(message = "El DNI es obligatorio")
    @Pattern(
        regexp = "\\d{8}",
        message = "El DNI debe tener 8 números"
    )
    private String dni;


    @NotBlank(message = "El código universitario es obligatorio")
    private String codigo;


    private String facultad;


    @NotBlank(message = "La contraseña es obligatoria")
    @Pattern(
        regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$",
        message = "La contraseña debe tener mínimo 8 caracteres, una mayúscula, una minúscula, un número y un símbolo"
    )
    private String password;

}