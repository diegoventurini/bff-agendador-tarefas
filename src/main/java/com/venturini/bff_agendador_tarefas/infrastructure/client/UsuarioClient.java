package com.venturini.bff_agendador_tarefas.infrastructure.client;


import com.venturini.bff_agendador_tarefas.business.dto.in_Request.EnderecoDTORequest;
import com.venturini.bff_agendador_tarefas.business.dto.in_Request.LoginDTORequest;
import com.venturini.bff_agendador_tarefas.business.dto.in_Request.TelefoneDTORequest;
import com.venturini.bff_agendador_tarefas.business.dto.in_Request.UsuarioDTORequest;
import com.venturini.bff_agendador_tarefas.business.dto.out_Response.EnderecoDTOResponse;
import com.venturini.bff_agendador_tarefas.business.dto.out_Response.TelefoneDTOResponse;
import com.venturini.bff_agendador_tarefas.business.dto.out_Response.UsuarioDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping
    UsuarioDTOResponse buscaUsuarioPorEmail(@RequestParam("email") String email,
                                            @RequestHeader("Authorization") String token);

    @PostMapping
    UsuarioDTOResponse salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTOResponse);


    @PostMapping("/login")
    String login(@RequestBody LoginDTORequest usuarioDTOResponse);


    @DeleteMapping("/{email}")
    void deletaUsuarioPorEmail(@PathVariable String email,
                               @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioDTOResponse atualizaDadoUsuario(@RequestBody UsuarioDTORequest dto,
                                           @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDTOResponse atualizaEndereco(@RequestBody EnderecoDTORequest dto,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneDTOResponse atualizaTelefone(@RequestBody TelefoneDTORequest dto,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String token);

    @PostMapping("/endereco")
    EnderecoDTOResponse cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                         @RequestHeader("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDTOResponse cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                         @RequestHeader("Authorization") String token);
}
