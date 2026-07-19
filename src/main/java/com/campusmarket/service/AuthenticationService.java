package com.campusmarket.service;


import com.campusmarket.dto.LoginDTO;
import com.campusmarket.dto.LoginResponse;
import com.campusmarket.dto.UsuarioDTO;
import com.campusmarket.entity.Usuario;
import com.campusmarket.security.JwtService;
import com.campusmarket.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.campusmarket.dto.RecuperarPasswordDTO;
import com.campusmarket.dto.NuevaPasswordDTO;


@Service
@RequiredArgsConstructor
public class AuthenticationService {



    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    
    public LoginResponse login(LoginDTO dto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getCorreo(),
                        dto.getPassword()
                )
        );

        Usuario usuario = usuarioRepository
                .findByCorreo(dto.getCorreo())
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado")
                );
        System.out.println(
                passwordEncoder.matches(
                        dto.getPassword(),
                        usuario.getPassword()
                )
                );

        String token = jwtService.generarToken(usuario.getCorreo());

        return new LoginResponse(
                token,
                new UsuarioDTO(
                        usuario.getId(),
                        usuario.getNombre(),
                        usuario.getCorreo(),
                        usuario.getRol(),
                        usuario.getDni(),
                        usuario.getCodigo(),
                        usuario.getFacultad(),
                        usuario.getFoto(),
                        usuario.getNumeroCelular()
                )
        );

        }

        public String generarCodigo(RecuperarPasswordDTO dto) {


                Usuario usuario = usuarioRepository
                        .findByCorreo(dto.getCorreo())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Correo no registrado"
                                )
                        );


                String codigo =
                        String.valueOf(
                                (int)(Math.random() * 900000) + 100000
                        );


                usuario.setCodigoRecuperacion(codigo);


                usuarioRepository.save(usuario);


                return "Código de recuperación generado: " + codigo;

                }
        public String cambiarPassword(NuevaPasswordDTO dto) {


        Usuario usuario = usuarioRepository
                .findByCorreo(dto.getCorreo())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Usuario no encontrado"
                        )
                );


        if(!usuario.getCodigoRecuperacion()
                .equals(dto.getCodigo())){


                throw new RuntimeException(
                        "Código incorrecto"
                );

        }


        usuario.setPassword(
                passwordEncoder.encode(
                        dto.getNuevaPassword()
                )
        );


        usuario.setCodigoRecuperacion(null);


        usuarioRepository.save(usuario);


        return "Contraseña actualizada correctamente";

        }


}