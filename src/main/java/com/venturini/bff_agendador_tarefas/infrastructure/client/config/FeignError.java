package com.venturini.bff_agendador_tarefas.infrastructure.client.config;

import com.venturini.bff_agendador_tarefas.infrastructure.exceptions.BusinessException;
import com.venturini.bff_agendador_tarefas.infrastructure.exceptions.ConflictException;
import com.venturini.bff_agendador_tarefas.infrastructure.exceptions.IllegalArgumentException;
import com.venturini.bff_agendador_tarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.venturini.bff_agendador_tarefas.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FeignError implements ErrorDecoder {

    @Override
    // Tratamento de cada response code http
    public Exception decode(String s, Response response) {

        String mensagemErro = mensagemError(response);

        switch (response.status()) {
            case 409:
                return new ConflictException("Error: " + mensagemErro);
            case 403:
                return new ResourceNotFoundException("Error: " + mensagemErro);
            case 401:
                return new UnauthorizedException("Error: " + mensagemErro);
            case 400:
                return new IllegalArgumentException("Error: " + mensagemErro);
            default:
                return new BusinessException("Error: " + mensagemErro);
        }
    }

    private String mensagemError(Response response) {
        try {
            if (Objects.isNull(response.body())) {
                return "";
            }
            return new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
