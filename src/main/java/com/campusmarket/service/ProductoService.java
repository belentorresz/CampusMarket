package com.campusmarket.service;

import com.campusmarket.dto.ProductoDTO;
import com.campusmarket.entity.Categoria;
import com.campusmarket.entity.Producto;
import com.campusmarket.entity.Usuario;
import com.campusmarket.exception.RecursoNoEncontradoException;
import com.campusmarket.repository.ProductoRepository;
import com.campusmarket.repository.UsuarioRepository;
import com.campusmarket.repository.CategoriaRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductoService {


    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;


    public ProductoService(
            ProductoRepository productoRepository,
            UsuarioRepository usuarioRepository,
            CategoriaRepository categoriaRepository){

        this.productoRepository = productoRepository;
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;

    }


    public Producto crear(ProductoDTO dto){

        Usuario usuario =
            usuarioRepository.findById(dto.getUsuarioId())
            .orElseThrow(() ->
                    new RecursoNoEncontradoException(
                            "Usuario no encontrado"
                    )
            );
                
        Categoria categoria =
            categoriaRepository.findById(dto.getCategoriaId())
            .orElseThrow(() ->
                    new RecursoNoEncontradoException(
                            "Categoría no encontrada"
                    )
            );
        
        Producto producto = new Producto();

        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setEstado("DISPONIBLE");
        producto.setImagen(dto.getImagen());
        producto.setCondicion(dto.getCondicion());
        producto.setFecha(LocalDateTime.now());
        producto.setUsuario(usuario);
        producto.setCategoria(categoria);


        return productoRepository.save(producto);

    }

    public List<Producto> listar(){

        return productoRepository.findByEstadoNot("VENDIDO");

    }

    public List<Producto> obtenerPorUsuario(Long id){

        return productoRepository.findByUsuarioId(id);

    }

    public List<Producto> buscar(
        String nombre,
        String estado,
        Long categoriaId
    ){

        if(nombre != null){

            return productoRepository
                    .findByNombreContainingIgnoreCase(nombre);

        }


        if(estado != null){

            return productoRepository
                    .findByEstado(estado);

        }


        if(categoriaId != null){

            return productoRepository
                    .findByCategoriaId(categoriaId);

        }


        return productoRepository.findAll();

    }

    public Producto cambiarEstado(Long id, String estado) {

        if (!estado.equals("DISPONIBLE") &&
            !estado.equals("RESERVADO") &&
            !estado.equals("VENDIDO")) {

            throw new RuntimeException("Estado inválido");
        }

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setEstado(estado);

        return productoRepository.save(producto);
    }


    
}