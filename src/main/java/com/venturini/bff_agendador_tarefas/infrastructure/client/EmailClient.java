package com.venturini.bff_agendador_tarefas.infrastructure.client;

import com.venturini.bff_agendador_tarefas.business.dto.out.TarefaDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// nome da API          // url da porta
@FeignClient(name = "notificacao", url = "${notificacao.url}")
public interface EmailClient {

    @PostMapping
    void enviarEmail(@RequestBody TarefaDTOResponse tarefaDTO);
}
