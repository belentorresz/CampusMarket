package com.campusmarket.service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;


@Service
public class ArchivoService {


    private final Path carpeta =
            Paths.get("uploads/productos");

    private final Path carpetaPerfil =
        Paths.get("uploads/perfiles");


    public String guardarImagen(MultipartFile archivo) throws IOException {


        if (archivo.isEmpty()) {
            throw new RuntimeException("El archivo está vacío");
        }


        if (!Files.exists(carpeta)) {
            Files.createDirectories(carpeta);
        }


        String extension = "";

        String nombreOriginal = archivo.getOriginalFilename();


        if(nombreOriginal != null && nombreOriginal.contains(".")){

            extension =
                    nombreOriginal.substring(
                            nombreOriginal.lastIndexOf(".")
                    );

        }


        String nombreArchivo =
                UUID.randomUUID()
                + extension;


        Path rutaArchivo =
                carpeta.resolve(nombreArchivo);


        Files.copy(
                archivo.getInputStream(),
                rutaArchivo,
                StandardCopyOption.REPLACE_EXISTING
        );


        return nombreArchivo;
    }

    public String guardarFotoPerfil(MultipartFile archivo) throws IOException {


    if (archivo.isEmpty()) {
        throw new RuntimeException("El archivo está vacío");
    }


    if (!Files.exists(carpetaPerfil)) {
        Files.createDirectories(carpetaPerfil);
    }


    String extension = "";

    String nombreOriginal =
            archivo.getOriginalFilename();


    if(nombreOriginal != null &&
       nombreOriginal.contains(".")){

        extension =
        nombreOriginal.substring(
            nombreOriginal.lastIndexOf(".")
        );

    }


    String nombreArchivo =
            UUID.randomUUID()
            + extension;


    Path rutaArchivo =
            carpetaPerfil.resolve(nombreArchivo);


    Files.copy(
            archivo.getInputStream(),
            rutaArchivo,
            StandardCopyOption.REPLACE_EXISTING
    );


    return nombreArchivo;
}
}