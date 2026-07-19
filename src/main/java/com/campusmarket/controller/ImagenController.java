package com.campusmarket.controller;


import com.campusmarket.dto.ImagenDTO;
import com.campusmarket.entity.ImagenProducto;
import com.campusmarket.service.ImagenService;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/imagenes")
@CrossOrigin
public class ImagenController {


    private final ImagenService imagenService;



    public ImagenController(
            ImagenService imagenService){

        this.imagenService = imagenService;

    }



    @PostMapping
    public ImagenProducto guardar(
            @RequestBody ImagenDTO dto){

        return imagenService.guardar(dto);

    }


}