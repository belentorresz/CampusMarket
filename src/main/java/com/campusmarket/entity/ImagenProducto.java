package com.campusmarket.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name="imagenes_producto")
@Data
public class ImagenProducto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String ruta;


    @ManyToOne
    @JoinColumn(name="producto_id")
    private Producto producto;


}