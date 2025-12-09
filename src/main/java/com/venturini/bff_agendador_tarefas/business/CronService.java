package com.venturini.bff_agendador_tarefas.business;


import com.venturini.bff_agendador_tarefas.business.dto.in_Request.LoginDTORequest;
import com.venturini.bff_agendador_tarefas.business.dto.out_Response.TarefasDTOResponse;
import com.venturini.bff_agendador_tarefas.business.enums.StatusNotificacaoEnum;
import lombok.AllArgsConstructor;
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

    private final TarefasService tarefasService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;


    //Cron
    @Scheduled(cron = "${cron.horario}")

    public void buscaTarefasProximaHora() {
        String token = login(converterParaDTORequest());
        log.info("Iniciada a busca de tarefas");
        LocalDateTime horaAtual = LocalDateTime.now();
        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);
        // Qualquer tarefa entre a horaAtual e a horaFutura
        // Hora atual + 1
        // Se é 22h - qualquer tarefa entre 22h e 23h

        List<TarefasDTOResponse> listaTarefas = tarefasService.buscaTarefasAgendadasPorPeriodo(horaAtual, horaFutura, token);
        log.info("Tarefas Encontradas " + listaTarefas);
        listaTarefas.forEach(tarefa -> {
            emailService.enviaEmail(tarefa);
            log.info("Email enviado para o usuário " + tarefa.getEmailUsuario());
            tarefasService.alterarStatus(StatusNotificacaoEnum.NOTIFICADO, tarefa.getId(),
            token);
        });
        log.info("Finalizada a busca e notificação de tarefas");
    }

    public String login(LoginDTORequest dto) {
        return  usuarioService.loginUsuario(dto);
    }

    public LoginDTORequest converterParaDTORequest() {
        return LoginDTORequest.builder()
                .email(email)
                .senha(senha)
                .build();
    }

}
