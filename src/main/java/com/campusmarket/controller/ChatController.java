package com.campusmarket.controller;


import com.campusmarket.dto.ChatDTO;
import com.campusmarket.dto.ChatDetalleDTO;
import com.campusmarket.dto.MensajeDTO;
import com.campusmarket.entity.Chat;
import com.campusmarket.entity.Mensaje;
import com.campusmarket.service.ChatService;
import com.campusmarket.service.MensajeService;

import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/chat")
@CrossOrigin("*")
public class ChatController {


    private final ChatService chatService;

    private final MensajeService mensajeService;



    public ChatController(
            ChatService chatService,
            MensajeService mensajeService
    ){

        this.chatService = chatService;
        this.mensajeService = mensajeService;

    }




    @PostMapping
    public Chat crearChat(
            @RequestBody Chat chat
    ){

        return chatService.crearChat(chat);

    }




    @GetMapping
    public List<Chat> listarChats(){

        return chatService.listarChats();

    }




@GetMapping("/{id}")
public ChatDetalleDTO obtenerChat(
        @PathVariable Long id
){

    return chatService.buscarDetalle(id);

}





    @GetMapping("/{id}/mensajes")
    public List<MensajeDTO> mensajes(
            @PathVariable Long id
    ){

        return mensajeService.listarPorChat(id);

    }





    @GetMapping("/usuario/{id}")
    public List<ChatDTO> chatsUsuario(
            @PathVariable Long id
    ){

        return chatService.chatsUsuarioDTO(id);

    }





    @PostMapping("/{id}/mensaje")
    public MensajeDTO enviarMensaje(
            @PathVariable Long id,
            @RequestBody Mensaje mensaje
    ){

        mensaje.setChatId(id);

        return mensajeService.guardar(mensaje);

    }


}