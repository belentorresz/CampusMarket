package com.campusmarket.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
@Table(name="mensajes")
public class Mensaje {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long chatId;


    private Long remitente;


    @Column(columnDefinition = "TEXT")
    private String mensaje;


    private LocalDateTime fecha = LocalDateTime.now();


    private Boolean leido = false;

}