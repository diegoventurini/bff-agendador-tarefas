package com.venturini.bff_agendador_tarefas.infrastructure.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // classe de configuração
public class FeignConfig {

    @Bean
    public FeignError feignError() {
        return new FeignError();
    }
}
