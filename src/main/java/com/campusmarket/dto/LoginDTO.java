package com.campusmarket.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class LoginDTO {


    @Email
    @NotBlank
    private String correo;


    @NotBlank
    private String password;


}