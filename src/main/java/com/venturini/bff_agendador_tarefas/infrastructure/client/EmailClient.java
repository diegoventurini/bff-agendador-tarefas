package com.venturini.bff_agendador_tarefas.infrastructure.client;


import com.venturini.bff_agendador_tarefas.business.dto.out_Response.TarefasDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "notificacao", url = "${notificacao.url}")
public interface EmailClient {

    void enviaEmail(@RequestBody TarefasDTOResponse dto);
}
