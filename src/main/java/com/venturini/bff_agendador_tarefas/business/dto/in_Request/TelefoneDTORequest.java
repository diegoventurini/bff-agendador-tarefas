package com.venturini.bff_agendador_tarefas.business.dto.in_Request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class TelefoneDTORequest {

    private String numero;
    private String ddd;
}
