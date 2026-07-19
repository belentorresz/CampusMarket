package com.campusmarket.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;


@Service
public class JwtService {


    private final String SECRET_KEY =
            "CampusMarketSecretKeyCampusMarketSecretKey123";


    public String generarToken(String correo){


        return Jwts.builder()
                .subject(correo)
                .issuedAt(new Date())
                .expiration(
                        new Date(System.currentTimeMillis()+86400000)
                )
                .signWith(
                        Keys.hmacShaKeyFor(
                                SECRET_KEY.getBytes(StandardCharsets.UTF_8)
                        )
                )
                .compact();

    }



    public String extraerCorreo(String token){


        return Jwts.parser()
                .verifyWith(
                    Keys.hmacShaKeyFor(
                       SECRET_KEY.getBytes(StandardCharsets.UTF_8)
                    )
                )
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();

    }

}