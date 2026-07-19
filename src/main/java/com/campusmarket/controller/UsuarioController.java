package com.campusmarket.controller;

import com.campusmarket.dto.CambiarPasswordDTO;
import com.campusmarket.dto.UsuarioDTO;
import com.campusmarket.entity.Usuario;
import com.campusmarket.repository.UsuarioRepository;
import com.campusmarket.security.JwtService;
import com.campusmarket.service.ArchivoService;
import com.campusmarket.service.UsuarioService;
import java.io.IOException;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    private final ArchivoService archivoService;

    @GetMapping("/me")
    public ResponseEntity<UsuarioDTO> usuarioActual(
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.replace("Bearer ", "");

        String correo = jwtService.extraerCorreo(token);

        return ResponseEntity.ok(
                usuarioService.obtenerPorCorreo(correo)
        );

    }

    @GetMapping("/test")
    public ResponseEntity<String> test(){

        return ResponseEntity.ok("UsuarioController funcionando");

    }

   @PostMapping("/{id}/foto")
    public ResponseEntity<?> subirFoto(
            @PathVariable Long id,
            @RequestParam("archivo") MultipartFile archivo
    ) throws IOException {


        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado")
                );


        String nombreFoto =
                archivoService.guardarFotoPerfil(archivo);


        usuario.setFoto(nombreFoto);


        usuarioRepository.save(usuario);


        return ResponseEntity.ok(usuario);

    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(
            @PathVariable Long id,
            @RequestBody UsuarioDTO usuarioDTO){

        UsuarioDTO actualizado = usuarioService.actualizarUsuario(id, usuarioDTO);

        return ResponseEntity.ok(actualizado);
    }

    @PutMapping("/cambiar-password")
    public String cambiarPassword(
            @RequestBody CambiarPasswordDTO dto
    ){

        usuarioService.cambiarPassword(dto);

        return "Contraseña actualizada";

    }

}