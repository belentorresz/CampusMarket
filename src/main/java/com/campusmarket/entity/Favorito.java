package com.campusmarket.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(
    name = "favoritos",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {
                "usuario_id",
                "producto_id"
            }
        )
    }
)
public class Favorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

}