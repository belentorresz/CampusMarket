package com.campusmarket.repository;


import com.campusmarket.entity.Producto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductoRepository 
extends JpaRepository<Producto, Long>{


    List<Producto> findByUsuarioId(Long usuarioId);


    List<Producto> findByEstado(String estado);


    List<Producto> findByEstadoNot(String estado);


    List<Producto> findByNombreContainingIgnoreCase(String nombre);


    List<Producto> findByCategoriaId(Long categoriaId);


}