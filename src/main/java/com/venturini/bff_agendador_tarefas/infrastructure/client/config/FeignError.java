package com.venturini.bff_agendador_tarefas.infrastructure.client.config;

import com.venturini.bff_agendador_tarefas.infrastructure.exceptions.BusinessException;
import com.venturini.bff_agendador_tarefas.infrastructure.exceptions.ConflictException;
import com.venturini.bff_agendador_tarefas.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignError implements ErrorDecoder {

    @Override
    // Tratamento de cada response code http
    public Exception decode(String methodKey, Response response) {

        switch (response.status()) {
            case 409:
                return new ConflictException("Error: atributo já existente.");
            case 403:
                return new ReflectiveOperationException("Error: atributo não encontrado.");
            case 401:
                return new UnauthorizedException("Error: usuário não autorizado.");
            default:
                return new BusinessException("Erro de servidor.");
        }
    }
}
