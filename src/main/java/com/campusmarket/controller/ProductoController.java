package com.campusmarket.controller;


import com.campusmarket.dto.ProductoDTO;
import com.campusmarket.entity.Producto;
import com.campusmarket.repository.ProductoRepository;
import com.campusmarket.service.ProductoService;
import org.springframework.web.multipart.MultipartFile;
import com.campusmarket.service.ArchivoService;
import org.springframework.http.HttpStatus;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/productos")
@CrossOrigin
public class ProductoController {

    

    private final ProductoService productoService;
    private final ArchivoService archivoService;


    private final ProductoRepository productoRepository;

    public ProductoController(
            ProductoService productoService,
            ProductoRepository productoRepository,
            ArchivoService archivoService) {

        this.productoService = productoService;
        this.productoRepository = productoRepository;
        this.archivoService = archivoService;
    }


    @PostMapping
    public Producto crear(
            @Valid @RequestBody ProductoDTO dto){

        return productoService.crear(dto);

    }



    @GetMapping
    public List<Producto> listar(){

        return productoService.listar();

    }

    @GetMapping("/{id}")
    public Producto obtenerProducto(
            @PathVariable Long id){

        return productoRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Producto no encontrado")
                );

    }

    @GetMapping("/buscar")
    public List<Producto> buscar(

            @RequestParam(required=false)
            String nombre,


            @RequestParam(required=false)
            String estado,


            @RequestParam(required=false)
            Long categoriaId

    ){

        return productoService.buscar(
                nombre,
                estado,
                categoriaId
        );

    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<?> cambiarEstado(
            @PathVariable Long id,
            @RequestBody Map<String, String> datos) {

        String estado = datos.get("estado");

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setEstado(estado);

        productoRepository.save(producto);

        return ResponseEntity.ok(producto);
    }

    @PostMapping("/{id}/imagen")
    public ResponseEntity<?> subirImagen(
            @PathVariable Long id,
            @RequestParam("archivo") MultipartFile archivo
    ) {

        try {

            Producto producto =
                    productoRepository.findById(id)
                    .orElseThrow(() ->
                            new RuntimeException("Producto no encontrado")
                    );


            String nombreImagen =
                    archivoService.guardarImagen(archivo);


            producto.setImagen(nombreImagen);


            productoRepository.save(producto);


            return ResponseEntity.ok(producto);


        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());

        }
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Producto>> productosUsuario(
            @PathVariable Long id
    ){

        return ResponseEntity.ok(
                productoService.obtenerPorUsuario(id)
        );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(
            @PathVariable Long id
    ){

        productoRepository.deleteById(id);

        return ResponseEntity.ok(
                "Producto eliminado"
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(
            @PathVariable Long id,
            @RequestBody ProductoDTO dto
    ){

        Producto producto =
                productoRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Producto no encontrado")
                );


        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setCondicion(dto.getCondicion());


        return ResponseEntity.ok(
                productoRepository.save(producto)
        );

    }

}