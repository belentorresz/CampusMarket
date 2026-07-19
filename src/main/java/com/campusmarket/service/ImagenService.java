package com.campusmarket.service;


import com.campusmarket.dto.ImagenDTO;
import com.campusmarket.entity.ImagenProducto;
import com.campusmarket.entity.Producto;
import com.campusmarket.repository.ImagenProductoRepository;
import com.campusmarket.repository.ProductoRepository;

import org.springframework.stereotype.Service;



@Service
public class ImagenService {


    private final ImagenProductoRepository imagenRepository;

    private final ProductoRepository productoRepository;



    public ImagenService(
            ImagenProductoRepository imagenRepository,
            ProductoRepository productoRepository){

        this.imagenRepository = imagenRepository;
        this.productoRepository = productoRepository;

    }



    public ImagenProducto guardar(ImagenDTO dto){


        Producto producto =
        productoRepository.findById(dto.getProductoId())
        .orElseThrow();



        ImagenProducto imagen = new ImagenProducto();

        imagen.setRuta(dto.getRuta());

        imagen.setProducto(producto);



        return imagenRepository.save(imagen);

    }


}