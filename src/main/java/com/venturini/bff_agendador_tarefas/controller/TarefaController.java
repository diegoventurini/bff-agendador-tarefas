package com.venturini.bff_agendador_tarefas.controller;

import com.venturini.bff_agendador_tarefas.business.TarefaService;
import com.venturini.bff_agendador_tarefas.business.dto.in.TarefaDTORequest;
import com.venturini.bff_agendador_tarefas.business.dto.out.TarefaDTOResponse;
import com.venturini.bff_agendador_tarefas.business.enums.StatusNotificacaoEnum;
import com.venturini.bff_agendador_tarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tarefas")
// Swagger
@Tag(name = "Tarefas", description = "Cadastro tarefas de usuários") // nome e descrição da controller
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    // @Operation -> descrição do método
    @Operation(summary = "Salvar Tarefas de Usuários", description = "Cria uma nova tarefa")
    // @ApiResponse -> Status code
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefaDTOResponse> gravarTarefa(@RequestBody TarefaDTORequest tarefaDTO,
                                                          @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefaService.gravaTarefa(tarefaDTO, token));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Buscar Lista Tarefas por Período",
            description = "Busca lista de tarefas cadastradas por período")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<List<TarefaDTOResponse>> buscarListaTarefaAgendadaPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefaService.buscaListaTarefaAgendadaPorPeriodo(dataInicial, dataFinal, token));
    }

    @GetMapping
    @Operation(summary = "Buscar Lista Tarefas por Email do usuário",
            description = "Busca lista de tarefas por email do usuário")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "403", description = "Email não encontrado")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<List<TarefaDTOResponse>> buscarListaTarefaPorEmail(
            @RequestHeader(name = "Authorization", required = false) String token) {

        return ResponseEntity.ok(tarefaService.buscaListaTarefaPorEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Deletar Tarefas por Id", description = "Deleta tarefas cadastradas por id")
    @ApiResponse(responseCode = "200", description = "Tarefa deletada com sucesso")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrada")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletarTarefaPorId(@RequestParam("id") String id,
                                  @RequestHeader(name = "Authorization", required = false) String token){
        tarefaService.deletaTarefaPorId(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Alterar Status de Notificação de Tarefa", description = "Altera o status da notificação da tarefa")
    @ApiResponse(responseCode = "200", description = "Status alterado com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrada")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<TarefaDTOResponse> alterarStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum statusNotificacaoEnum,
                                                                      @RequestParam("id") String id,
                                                                      @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefaService.alteraStatusNotificacao(statusNotificacaoEnum, id, token));
    }

    @PutMapping
    @Operation(summary = "Alterar Dados da Tarefa", description = "Altera dados da tarefa cadastrada")
    @ApiResponse(responseCode = "200", description = "Tarefa alterada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrada")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<TarefaDTOResponse> atualizarTarefas(@RequestBody TarefaDTORequest tarefaDTO,
                                                              @RequestParam("id") String id,
                                                              @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefaService.updateTarefas(tarefaDTO, id, token));
    }

}
