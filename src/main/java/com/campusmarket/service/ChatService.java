package com.campusmarket.service;

import com.campusmarket.dto.ChatDTO;
import com.campusmarket.dto.ChatDetalleDTO;
import com.campusmarket.entity.Chat;
import com.campusmarket.entity.Usuario;
import com.campusmarket.exception.RecursoNoEncontradoException;
import com.campusmarket.repository.ChatRepository;
import com.campusmarket.repository.UsuarioRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatService {

    private final ChatRepository chatRepository;
    private final UsuarioRepository usuarioRepository;

    public ChatService(
        ChatRepository chatRepository,
        UsuarioRepository usuarioRepository
){

    this.chatRepository = chatRepository;
    this.usuarioRepository = usuarioRepository;

}

    public Chat crearChat(Chat chat) {

        Optional<Chat> existente = chatRepository
                .findByUsuario1AndUsuario2(
                        chat.getUsuario1(),
                        chat.getUsuario2()
                );

        if (existente.isPresent()) {
            return existente.get();
        }

        existente = chatRepository
                .findByUsuario2AndUsuario1(
                        chat.getUsuario1(),
                        chat.getUsuario2()
                );

        if (existente.isPresent()) {
            return existente.get();
        }

        return chatRepository.save(chat);
    }

    public List<Chat> listarChats() {
        return chatRepository.findAll();
    }

    public Chat buscarPorId(Long id) {
        return chatRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Chat no encontrado"));
    }

    public List<Chat> chatsUsuario(Long usuarioId) {
        return chatRepository.findByUsuario1OrUsuario2(
                usuarioId,
                usuarioId
        );
    }

    public List<ChatDTO> chatsUsuarioDTO(Long usuarioId){


        return chatRepository
                .findByUsuario1OrUsuario2(
                        usuarioId,
                        usuarioId
                )
                .stream()
                .map(chat -> {


                    Long otroUsuario;



                    if(usuarioId.equals(chat.getUsuario1())){

                        otroUsuario = chat.getUsuario2();

                    }else{

                        otroUsuario = chat.getUsuario1();

                    }



                    Usuario usuario = null;


                    if(otroUsuario != null){

                        usuario = usuarioRepository
                                .findById(otroUsuario)
                                .orElse(null);

                    }



                    return new ChatDTO(

                            chat.getId(),

                            otroUsuario,

                            usuario != null
                                    ?
                                    usuario.getNombre()
                                    :
                                    "Usuario",

                            chat.getFechaCreacion()

                    );


                })
                .toList();

    }

    public ChatDetalleDTO buscarDetalle(Long id){


    Chat chat = chatRepository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException("Chat no encontrado")
            );


    Usuario usuario1 =
            usuarioRepository.findById(chat.getUsuario1())
            .orElse(null);


    Usuario usuario2 =
            usuarioRepository.findById(chat.getUsuario2())
            .orElse(null);



    return new ChatDetalleDTO(

            chat.getId(),

            chat.getUsuario1(),

            usuario1 != null
            ? usuario1.getNombre()
            : "Usuario",


            chat.getUsuario2(),

            usuario2 != null
            ? usuario2.getNombre()
            : "Usuario",


            chat.getFechaCreacion()

    );


}

}