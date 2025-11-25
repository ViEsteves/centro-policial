package com.centro.policial.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DepartamentoDTO {
    private Long id;

    @NotBlank
    private String nome;

    private String descricao;
}
