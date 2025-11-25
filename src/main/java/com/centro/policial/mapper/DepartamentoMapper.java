package com.centro.policial.mapper;

import com.centro.policial.dto.DepartamentoDTO;
import com.centro.policial.entity.Departamento;

public class DepartamentoMapper {
    public static Departamento toEntity(DepartamentoDTO dto) {
        Departamento d = new Departamento();
        d.setId(dto.getId());
        d.setNome(dto.getNome());
        d.setDescricao(dto.getDescricao());
        return d;
    }
}
