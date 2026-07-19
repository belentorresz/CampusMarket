package com.campusmarket.controller;


import com.campusmarket.dto.LoginDTO;
import com.campusmarket.dto.LoginResponse;
import com.campusmarket.dto.RegistroDTO;
import com.campusmarket.dto.UsuarioDTO;

import com.campusmarket.service.AuthenticationService;
import com.campusmarket.service.UsuarioService;
import com.campusmarket.dto.RecuperarPasswordDTO;
import com.campusmarket.dto.NuevaPasswordDTO;


import jakarta.validation.Valid;


import lombok.RequiredArgsConstructor;


import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {



    private final UsuarioService usuarioService;

    private final AuthenticationService authenticationService;




    @PostMapping("/register")
    public ResponseEntity<UsuarioDTO> registrar(
            @Valid @RequestBody RegistroDTO dto
    ){

        return ResponseEntity.ok(
                usuarioService.registrar(dto)
        );

    }




    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginDTO dto
    ){

        return ResponseEntity.ok(
                authenticationService.login(dto)
        );

    }

        @PostMapping("/recuperar")
        public ResponseEntity<?> recuperar(
                @RequestBody RecuperarPasswordDTO dto
        ){

        try{

                return ResponseEntity.ok(
                authenticationService.generarCodigo(dto)
                );


        }catch(RuntimeException e){


                return ResponseEntity
                        .badRequest()
                        .body(e.getMessage());


        }

        }


        @PostMapping("/cambiar-password")
        public String cambiarPassword(
                @RequestBody NuevaPasswordDTO dto
        ){
        return authenticationService.cambiarPassword(dto);
        }


}