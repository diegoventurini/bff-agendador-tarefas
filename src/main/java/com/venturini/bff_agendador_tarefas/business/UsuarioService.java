package com.venturini.bff_agendador_tarefas.business;

import com.venturini.bff_agendador_tarefas.business.dto.in.EnderecoDTORequest;
import com.venturini.bff_agendador_tarefas.business.dto.in.LoginDTORequest;
import com.venturini.bff_agendador_tarefas.business.dto.in.TelefoneDTORequest;
import com.venturini.bff_agendador_tarefas.business.dto.in.UsuarioDTORequest;
import com.venturini.bff_agendador_tarefas.business.dto.out.EnderecoDTOResponse;
import com.venturini.bff_agendador_tarefas.business.dto.out.TelefoneDTOResponse;
import com.venturini.bff_agendador_tarefas.business.dto.out.UsuarioDTOResponse;
import com.venturini.bff_agendador_tarefas.business.dto.out.ViaCepDTOResponse;
import com.venturini.bff_agendador_tarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;

    // Salvar Usuário
    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTO) {
        return client.salvarUsuario(usuarioDTO);
    }

    public String loginUsuario(LoginDTORequest usuarioDTO) {
        return client.login(usuarioDTO);
    }

    public UsuarioDTOResponse buscaUsuarioPorEmail(String email, String token) {
        return client.buscarUsuarioPorEmail(email, token);
    }

    public void deletaUsuarioPorEmail(String email, String token) {

        client.deletarUsuarioPorEmail(email, token);
    }

    public UsuarioDTOResponse atualizaDadosUsuario(String token, UsuarioDTORequest usuarioDTO) {
        return client.atualizarUsuario(usuarioDTO, token);
    }

    public EnderecoDTOResponse atualizaEndereco(Long idEndereco, EnderecoDTORequest enderecoDTO, String token) {
        return client.atualizarEndereco(enderecoDTO, idEndereco, token);
    }

    public TelefoneDTOResponse atualizaTelefone(Long idTelefone, TelefoneDTORequest telefoneDTO, String token ) {
        return client.atualizarTelefone(telefoneDTO, idTelefone, token);
    }

    public EnderecoDTOResponse cadastraEndereco(String token, EnderecoDTORequest enderecoDTO) {
        return client.cadastrarEndereco(enderecoDTO, token);
    }

    public TelefoneDTOResponse cadastraTelefone(String token, TelefoneDTORequest telefoneDTO) {
        return client.cadastrarTelefone(telefoneDTO,token);
    }

    public ViaCepDTOResponse buscaEnderecoPorCEP(String cep) {
        return client.buscarDadosCep(cep);
    }

}
