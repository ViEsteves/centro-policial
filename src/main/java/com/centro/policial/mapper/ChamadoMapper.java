package com.centro.policial.mapper;

import com.centro.policial.dto.ChamadoDTO;
import com.centro.policial.entity.Chamado;
import com.centro.policial.entity.Departamento;

public class ChamadoMapper {
    public static Chamado toEntity(ChamadoDTO dto, Departamento departamento) {
        Chamado c = new Chamado();
        c.setId(dto.getId());
        c.setTitulo(dto.getTitulo());
        c.setDescricao(dto.getDescricao());
        c.setStatus(dto.getStatus());
        c.setDepartamento(departamento);
        return c;
    }
}
