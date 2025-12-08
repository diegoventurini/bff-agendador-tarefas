package com.venturini.bff_agendador_tarefas.business;

import com.venturini.bff_agendador_tarefas.business.dto.in_Request.EnderecoDTORequest;
import com.venturini.bff_agendador_tarefas.business.dto.in_Request.LoginDTORequest;
import com.venturini.bff_agendador_tarefas.business.dto.in_Request.TelefoneDTORequest;
import com.venturini.bff_agendador_tarefas.business.dto.in_Request.UsuarioDTORequest;
import com.venturini.bff_agendador_tarefas.business.dto.out_Response.EnderecoDTOResponse;
import com.venturini.bff_agendador_tarefas.business.dto.out_Response.TelefoneDTOResponse;
import com.venturini.bff_agendador_tarefas.business.dto.out_Response.UsuarioDTOResponse;
import com.venturini.bff_agendador_tarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor

public class UsuarioService {

    // injeção de dependência
    private final UsuarioClient usuarioClient;

    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTOResponse) {
        return usuarioClient.salvaUsuario(usuarioDTOResponse);
    }

    public String loginUsuario(LoginDTORequest usuarioDTOResponse) {
        return usuarioClient.login(usuarioDTOResponse);
    }


    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token) {
        return usuarioClient.buscaUsuarioPorEmail(email, token);
    }

    public void deletaUsuarioPorEmail(String email, String token) {
        usuarioClient.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioDTOResponse atualizaDadosUsuario(String token, UsuarioDTORequest dto) {
        return usuarioClient.atualizaDadoUsuario(dto, token);
    }

    public EnderecoDTOResponse atualizaEndereco(Long idEndereco, EnderecoDTORequest enderecoDTO, String token) {
        return usuarioClient.atualizaEndereco(enderecoDTO, idEndereco, token);
    }

    public TelefoneDTOResponse atualizaTelefone(Long idTelefone, TelefoneDTORequest telefoneDTO, String token) {
        return usuarioClient.atualizaTelefone(telefoneDTO, idTelefone, token);
    }

    public EnderecoDTOResponse cadastraEndereco(String token, EnderecoDTORequest dto) {
        return usuarioClient.cadastraEndereco(dto, token);

    }

    public TelefoneDTOResponse cadastraTelefone(String token, TelefoneDTORequest dto) {
        return usuarioClient.cadastraTelefone(dto, token);
    }
}
