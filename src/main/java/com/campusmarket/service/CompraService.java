package com.campusmarket.service;

import com.campusmarket.dto.CompraDTO;
import com.campusmarket.entity.Compra;
import com.campusmarket.entity.Producto;
import com.campusmarket.entity.Usuario;
import com.campusmarket.repository.CompraRepository;
import com.campusmarket.repository.ProductoRepository;
import com.campusmarket.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraService {

    private final CompraRepository compraRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;

    public CompraService(
            CompraRepository compraRepository,
            ProductoRepository productoRepository,
            UsuarioRepository usuarioRepository) {

        this.compraRepository = compraRepository;
        this.productoRepository = productoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Compra solicitarCompra(CompraDTO dto) {

        Producto producto = productoRepository.findById(dto.getProductoId())
                .orElseThrow(() ->
                        new RuntimeException("Producto no encontrado"));

        Usuario comprador = usuarioRepository.findById(dto.getCompradorId())
                .orElseThrow(() ->
                        new RuntimeException("Comprador no encontrado"));

        if (!producto.getEstado().equals("DISPONIBLE")) {
            throw new RuntimeException(
                    "El producto ya no está disponible");
        }

        if (producto.getUsuario().getId().equals(comprador.getId())) {
            throw new RuntimeException(
                    "No puedes comprar tu propio producto");
        }

        Compra compra = new Compra();

        compra.setProducto(producto);
        compra.setComprador(comprador);
        compra.setVendedor(producto.getUsuario());

        compra.setEstado("PENDIENTE");

        // Información del pago
        compra.setMetodoPago(dto.getMetodoPago());
        compra.setNumeroPago(dto.getNumeroPago());
        compra.setReferenciaPago(dto.getReferenciaPago());

        producto.setEstado("RESERVADO");

        productoRepository.save(producto);

        return compraRepository.save(compra);
    }

    public Compra aceptarCompra(Long compraId) {

        Compra compra = compraRepository.findById(compraId)
                .orElseThrow(() ->
                        new RuntimeException("Compra no encontrada"));

        compra.setEstado("ACEPTADA");

        Producto producto = compra.getProducto();

        producto.setEstado("VENDIDO");

        productoRepository.save(producto);

        return compraRepository.save(compra);
    }

    public Compra cancelarCompra(Long compraId) {

        Compra compra = compraRepository.findById(compraId)
                .orElseThrow(() ->
                        new RuntimeException("Compra no encontrada"));

        compra.setEstado("CANCELADA");

        Producto producto = compra.getProducto();

        producto.setEstado("DISPONIBLE");

        productoRepository.save(producto);

        return compraRepository.save(compra);
    }

    public List<Compra> comprasUsuario(Long usuarioId) {

        return compraRepository.findByCompradorId(usuarioId);

    }

    public List<Compra> ventasUsuario(Long usuarioId) {

        return compraRepository.findByVendedorId(usuarioId);

    }

}