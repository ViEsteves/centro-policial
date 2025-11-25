package com.centro.policial.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ChamadoDTO {
    private Long id;

    @NotBlank
    private String titulo;

    private String descricao;

    private String status;

    @NotNull
    private Long departamentoId;
}
