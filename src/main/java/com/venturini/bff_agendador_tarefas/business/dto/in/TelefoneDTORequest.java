package com.venturini.bff_agendador_tarefas.business.dto.in;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelefoneDTORequest {

    private String numero;
    private String ddd;
}
