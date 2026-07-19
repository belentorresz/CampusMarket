package com.campusmarket.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {


    @Bean
    public OpenAPI campusMarketAPI(){

        return new OpenAPI()
                .info(
                    new Info()
                    .title("CampusMarket API")
                    .description(
                        "API del marketplace universitario CampusMarket"
                    )
                    .version("1.0")
                );

    }

}