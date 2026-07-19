package com.campusmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {

    @GetMapping(value = {
            "/",
            "/login",
            "/registro",
            "/perfil",
            "/catalogo",
            "/editar-perfil",
            "/mis-productos",
            "/mis-compras",
            "/mis-ventas",
            "/favoritos",
            "/publicar",
            "/**/{path:[^.]*}"
    })
    public String index() {
        return "forward:/index.html";
    }
}