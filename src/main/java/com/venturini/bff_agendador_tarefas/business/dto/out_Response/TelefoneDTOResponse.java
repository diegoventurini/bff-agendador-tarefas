package com.venturini.bff_agendador_tarefas.business.dto.out_Response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class TelefoneDTOResponse {

    private Long id;
    private String numero;
    private String ddd;
}
