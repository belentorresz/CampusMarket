package com.campusmarket.repository;

import com.campusmarket.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository 
extends JpaRepository<Categoria,Long>{

}