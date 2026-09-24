package com.venturini.bff_agendador_tarefas.infrastructure.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    // CORS Global
    // Configuração do Cors para toda Controller

    // Olha esta configuração = (@Bean)
    @Bean
    public WebMvcConfigurer configCors() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // add todos endpoints
                        .allowedOrigins("http://localhost:4200")    //origem da aplicacao, pode trocar a porta
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE")    // tipo de requisição
                        .allowedHeaders("*")
                        .allowCredentials(true) // permite cookies e heads no authorization
                        .maxAge(360);
            }
        };
    }
}
