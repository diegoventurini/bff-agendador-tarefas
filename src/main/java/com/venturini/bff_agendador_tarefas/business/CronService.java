package com.venturini.bff_agendador_tarefas.business;

import com.venturini.bff_agendador_tarefas.business.dto.in.LoginDTORequest;
import com.venturini.bff_agendador_tarefas.business.dto.out.TarefaDTOResponse;
import com.venturini.bff_agendador_tarefas.business.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TarefaService tarefaService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;

    // Cron
    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefaProximaHora() {
        String token = login(converterParaLoginDTORequest());
        log.info("Iniciada a busca de tarefas");

        LocalDateTime horaAtual = LocalDateTime.now(); // Hora atual
        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1); // 1 hora da hora atual

        List<TarefaDTOResponse> listaTarefas =
                tarefaService.buscaListaTarefaAgendadaPorPeriodo(horaAtual, horaFutura, token);

        log.info("Tarefas encontradas: " + listaTarefas);
        listaTarefas.forEach(tarefa -> {
            emailService.enviaEmail(tarefa); // enviar um email
            log.info("Email enviado para o usuário: " + tarefa.getEmailUsuario());
            tarefaService.alteraStatusNotificacao(StatusNotificacaoEnum.NOTIFICADO, tarefa.getId(), token);
            // mudar o status de notificação
        });
        log.info("Finalizada a busca e notificação de Tarefas");
    }

    public String login(LoginDTORequest loginDTORequest) {
        return usuarioService.loginUsuario(loginDTORequest);
    }

    public LoginDTORequest converterParaLoginDTORequest() {
        return LoginDTORequest.builder()
                .email(email)
                .senha(senha)
                .build();
    }
}
