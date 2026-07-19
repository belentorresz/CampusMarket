package com.campusmarket.controller;

import com.campusmarket.dto.CompraDTO;
import com.campusmarket.entity.Compra;
import com.campusmarket.service.CompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
@RequiredArgsConstructor
public class CompraController {

    private final CompraService compraService;

    @PostMapping
    public Compra solicitar(
            @RequestBody CompraDTO dto){

        return compraService.solicitarCompra(dto);

    }

    @PutMapping("/{id}/aceptar")
    public Compra aceptar(
            @PathVariable Long id){

        return compraService.aceptarCompra(id);

    }

    @PutMapping("/{id}/cancelar")
    public Compra cancelar(
            @PathVariable Long id){

        return compraService.cancelarCompra(id);

    }

    @GetMapping("/comprador/{id}")
    public List<Compra> compras(
            @PathVariable Long id){

        return compraService.comprasUsuario(id);

    }

    @GetMapping("/vendedor/{id}")
    public List<Compra> ventas(
            @PathVariable Long id){

        return compraService.ventasUsuario(id);

    }

}