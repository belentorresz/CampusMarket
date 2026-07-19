package com.campusmarket.controller;


import com.campusmarket.dto.MensajeDTO;
import com.campusmarket.entity.Mensaje;
import com.campusmarket.service.MensajeService;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.handler.annotation.DestinationVariable;

import org.springframework.stereotype.Controller;



@Controller
public class ChatSocketController {



    private final MensajeService mensajeService;



    public ChatSocketController(
            MensajeService mensajeService
    ){

        this.mensajeService = mensajeService;

    }




    @MessageMapping("/chat/{chatId}")
    @SendTo("/topic/chat/{chatId}")
    public MensajeDTO enviarMensaje(

            @DestinationVariable Long chatId,

            Mensaje mensaje

    ){


        mensaje.setChatId(chatId);



        return mensajeService.guardar(mensaje);


    }


}