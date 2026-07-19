package com.campusmarket.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "compras")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "comprador_id")
    private Usuario comprador;

    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Usuario vendedor;

    private LocalDateTime fecha = LocalDateTime.now();

    private String estado;

    // YAPE, PLIN o TARJETA
    private String metodoPago;

    // Número del comprador (Yape o Plin)
    private String numeroPago;

    // Código de operación o referencia
    private String referenciaPago;
}