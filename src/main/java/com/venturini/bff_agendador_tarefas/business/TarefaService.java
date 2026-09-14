package com.venturini.bff_agendador_tarefas.business;


import com.venturini.bff_agendador_tarefas.business.dto.in.TarefaDTORequest;
import com.venturini.bff_agendador_tarefas.business.dto.out.TarefaDTOResponse;
import com.venturini.bff_agendador_tarefas.business.enums.StatusNotificacaoEnum;
import com.venturini.bff_agendador_tarefas.infrastructure.client.TarefasClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefasClient tarefasClient;

    public TarefaDTOResponse gravaTarefa(TarefaDTORequest tarefaDTO, String token) {
        return tarefasClient.gravarTarefa(tarefaDTO, token);
    }

    public List<TarefaDTOResponse> buscaListaTarefaAgendadaPorPeriodo(LocalDateTime dataInical,
                                                                      LocalDateTime dataFinal,
                                                                      String token) {
        return tarefasClient.buscarListaTarefaAgendadaPorPeriodo(dataInical, dataFinal, token);
    }

    public List<TarefaDTOResponse> buscaListaTarefaPorEmail(String token) {
        return tarefasClient.buscarListaTarefaPorEmail(token);
    }

    public void deletaTarefaPorId(String id, String token) {
        tarefasClient.deletarTarefaPorId(id, token);
    }

    public TarefaDTOResponse alteraStatusNotificacao(StatusNotificacaoEnum statusNotificacaoEnum,
                                                     String id, String token) {
        return tarefasClient.alterarStatusNotificacao(statusNotificacaoEnum, id, token);
    }

    public TarefaDTOResponse updateTarefas(TarefaDTORequest tarefaDTO, String id, String token) {
        return tarefasClient.atualizarTarefas(tarefaDTO, id, token);
    }
}
