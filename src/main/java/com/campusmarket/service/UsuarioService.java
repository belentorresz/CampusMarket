package com.campusmarket.service;


import com.campusmarket.dto.CambiarPasswordDTO;
import com.campusmarket.dto.RegistroDTO;
import com.campusmarket.dto.UsuarioDTO;
import com.campusmarket.entity.Rol;
import com.campusmarket.entity.Usuario;
import com.campusmarket.exception.RecursoNoEncontradoException;
import com.campusmarket.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UsuarioService {


    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;



    public UsuarioDTO registrar(RegistroDTO dto){


        if(usuarioRepository.existsByCorreo(dto.getCorreo())){
            throw new RecursoNoEncontradoException("El correo ya está registrado");
        }


        Usuario usuario = new Usuario();


        usuario.setNombre(dto.getNombre());

        usuario.setCorreo(dto.getCorreo());

        usuario.setDni(dto.getDni());

        usuario.setCodigo(dto.getCodigo());

        usuario.setFacultad(dto.getFacultad());

        usuario.setPassword(
                passwordEncoder.encode(dto.getPassword())
        );

        usuario.setRol(Rol.USUARIO);



        Usuario guardado = usuarioRepository.save(usuario);



        return new UsuarioDTO(
                guardado.getId(),
                guardado.getNombre(),
                guardado.getCorreo(),
                guardado.getRol(),
                guardado.getDni(),
                guardado.getCodigo(),
                guardado.getFacultad(),
                guardado.getFoto(),
                guardado.getNumeroCelular()
        );


    }

    public UsuarioDTO obtenerPorCorreo(String correo) {

        Usuario usuario = usuarioRepository
                .findByCorreo(correo)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Usuario no encontrado")
                );

        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getRol(),
                usuario.getDni(),
                usuario.getCodigo(),
                usuario.getFacultad(),
                usuario.getFoto(),
                usuario.getNumeroCelular()
        );

    }
    private UsuarioDTO convertirDTO(Usuario usuario){

        UsuarioDTO dto = new UsuarioDTO();

        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setCorreo(usuario.getCorreo());
        dto.setRol(usuario.getRol());
        dto.setDni(usuario.getDni());
        dto.setCodigo(usuario.getCodigo());
        dto.setFacultad(usuario.getFacultad());
        dto.setFoto(usuario.getFoto());
        dto.setNumeroCelular(usuario.getNumeroCelular());

        return dto;
    }

    public UsuarioDTO actualizarUsuario(Long id, UsuarioDTO dto){

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Usuario no encontrado")
                );


        usuario.setNombre(dto.getNombre());
        usuario.setCorreo(dto.getCorreo());
        usuario.setDni(dto.getDni());
        usuario.setCodigo(dto.getCodigo());
        usuario.setFacultad(dto.getFacultad());
        usuario.setNumeroCelular(dto.getNumeroCelular());


        Usuario actualizado = usuarioRepository.save(usuario);


        return convertirDTO(actualizado);
    }

    public void cambiarPassword(CambiarPasswordDTO dto){

        Usuario usuario =
                usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow();


        if(!passwordEncoder.matches(
                dto.getPasswordActual(),
                usuario.getPassword()
        )){

            throw new RuntimeException(
                    "La contraseña actual es incorrecta"
            );

        }


        usuario.setPassword(
                passwordEncoder.encode(
                        dto.getNuevaPassword()
                )
        );


        usuarioRepository.save(usuario);

    }

}