package com.venturini.bff_agendador_tarefas.business;



import com.venturini.bff_agendador_tarefas.business.dto.out_Response.TarefasDTOResponse;
import com.venturini.bff_agendador_tarefas.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {


    private final EmailClient emailClient;

    public void enviaEmail(TarefasDTOResponse dto) {
        emailClient.enviaEmail(dto);
    }

}
