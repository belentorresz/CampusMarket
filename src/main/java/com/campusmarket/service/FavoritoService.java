package com.campusmarket.service;


import com.campusmarket.dto.FavoritoDTO;
import com.campusmarket.entity.*;
import com.campusmarket.repository.*;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class FavoritoService {


    private final FavoritoRepository favoritoRepository;

    private final UsuarioRepository usuarioRepository;

    private final ProductoRepository productoRepository;



    public FavoritoService(
            FavoritoRepository favoritoRepository,
            UsuarioRepository usuarioRepository,
            ProductoRepository productoRepository){


        this.favoritoRepository = favoritoRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;

    }



    public Favorito agregar(FavoritoDTO dto){

        Optional<Favorito> existente =
                favoritoRepository.findByUsuarioIdAndProductoId(
                        dto.getUsuarioId(),
                        dto.getProductoId()
                );

        if(existente.isPresent()){
            return existente.get();
        }

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Producto producto = productoRepository.findById(dto.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Favorito favorito = new Favorito();
        favorito.setUsuario(usuario);
        favorito.setProducto(producto);

        return favoritoRepository.save(favorito);
    }



    public List<Favorito> listar(Long usuarioId){

        return favoritoRepository
                .findByUsuarioId(usuarioId);

    }



    public void eliminar(
            Long usuarioId,
            Long productoId){


        Favorito favorito =
        favoritoRepository
        .findByUsuarioIdAndProductoId(
                usuarioId,
                productoId
        )
        .orElseThrow();



        favoritoRepository.delete(favorito);

    }

    public boolean esFavorito(
        Long usuarioId,
        Long productoId){

        return favoritoRepository
                .existsByUsuarioIdAndProductoId(
                        usuarioId,
                        productoId
                );

    }

}