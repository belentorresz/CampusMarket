package com.campusmarket.service;


import com.campusmarket.dto.MensajeDTO;
import com.campusmarket.entity.Mensaje;
import com.campusmarket.entity.Usuario;
import com.campusmarket.repository.ChatRepository;
import com.campusmarket.repository.MensajeRepository;
import com.campusmarket.repository.UsuarioRepository;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


import java.util.List;



@Service
public class MensajeService {


    private final MensajeRepository mensajeRepository;

    private final ChatRepository chatRepository;

    private final UsuarioRepository usuarioRepository;

    private final SimpMessagingTemplate messagingTemplate;



    public MensajeService(
            MensajeRepository mensajeRepository,
            ChatRepository chatRepository,
            UsuarioRepository usuarioRepository,
            SimpMessagingTemplate messagingTemplate
    ){

        this.mensajeRepository = mensajeRepository;
        this.chatRepository = chatRepository;
        this.usuarioRepository = usuarioRepository;
        this.messagingTemplate = messagingTemplate;

    }





    public MensajeDTO guardar(Mensaje mensaje){
        chatRepository.findById(mensaje.getChatId())
                .orElseThrow(() ->
                        new RuntimeException("Chat no existe")
                );
        Mensaje guardado =
                mensajeRepository.save(mensaje);
        MensajeDTO dto =
                convertirDTO(guardado);
        messagingTemplate.convertAndSend(
                "/topic/chat/" + mensaje.getChatId(),
                dto
        );
        return dto;
    }


    public List<MensajeDTO> listarPorChat(Long chatId){


        return mensajeRepository
                .findByChatId(chatId)
                .stream()
                .map(this::convertirDTO)
                .toList();


    }




    private MensajeDTO convertirDTO(Mensaje mensaje){


        Usuario usuario =
                usuarioRepository.findById(
                        mensaje.getRemitente()
                )
                .orElse(null);



        return new MensajeDTO(

                mensaje.getId(),

                mensaje.getRemitente(),

                usuario != null
                        ? usuario.getNombre()
                        : "Usuario",

                mensaje.getMensaje(),

                mensaje.getFecha()

        );


    }



}