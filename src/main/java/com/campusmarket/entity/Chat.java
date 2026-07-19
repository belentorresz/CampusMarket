package com.campusmarket.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@Table(name = "chats")
public class Chat {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long usuario1;


    private Long usuario2;


    private LocalDateTime fechaCreacion = LocalDateTime.now();


    @Transient
    private List<Mensaje> mensajes;

}