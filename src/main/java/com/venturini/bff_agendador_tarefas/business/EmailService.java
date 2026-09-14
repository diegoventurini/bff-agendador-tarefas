package com.venturini.bff_agendador_tarefas.business;

import com.venturini.bff_agendador_tarefas.business.dto.out.TarefaDTOResponse;
import com.venturini.bff_agendador_tarefas.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;

    public void enviaEmail(TarefaDTOResponse tarefaDTO) {

        emailClient.enviarEmail(tarefaDTO);
    }
}
