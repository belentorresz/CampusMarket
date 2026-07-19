package com.campusmarket.repository;

import com.campusmarket.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompraRepository
        extends JpaRepository<Compra, Long>{

    List<Compra> findByCompradorId(Long id);

    List<Compra> findByVendedorId(Long id);

}