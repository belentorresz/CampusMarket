package com.campusmarket.controller;


import com.campusmarket.dto.FavoritoDTO;
import com.campusmarket.entity.Favorito;
import com.campusmarket.service.FavoritoService;

import org.springframework.web.bind.annotation.*;


import java.util.List;



@RestController
@RequestMapping("/api/favoritos")
@CrossOrigin
public class FavoritoController {



    private final FavoritoService favoritoService;



    public FavoritoController(
            FavoritoService favoritoService){

        this.favoritoService = favoritoService;

    }



    @PostMapping
    public Favorito agregar(
            @RequestBody FavoritoDTO dto){

        return favoritoService.agregar(dto);

    }



    @GetMapping("/{usuarioId}")
    public List<Favorito> listar(
            @PathVariable Long usuarioId){

        return favoritoService.listar(usuarioId);

    }



    @DeleteMapping
    public String eliminar(
            @RequestParam Long usuarioId,
            @RequestParam Long productoId){


        favoritoService.eliminar(
                usuarioId,
                productoId
        );


        return "Favorito eliminado";

    }

    @GetMapping("/existe")
    public boolean existe(
            @RequestParam Long usuarioId,
            @RequestParam Long productoId){

        return favoritoService.esFavorito(
                usuarioId,
                productoId
        );

    }

}