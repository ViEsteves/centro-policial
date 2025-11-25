package com.centro.policial.service;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import com.centro.policial.dto.DepartamentoDTO;
import com.centro.policial.entity.Departamento;
import com.centro.policial.mapper.DepartamentoMapper;
import com.centro.policial.repository.DepartamentoRepository;

@Service
public class DepartamentoService {
    private final DepartamentoRepository repo;
    public DepartamentoService(DepartamentoRepository repo) { this.repo = repo; }

    public Page<Departamento> list(String filtro, Pageable pageable) {
        if (filtro == null || filtro.isBlank()) return repo.findAll(pageable);
        return repo.findByNomeContainingIgnoreCase(filtro, pageable);
    }

    public Departamento get(Long id) { return repo.findById(id).orElseThrow(); }

    public Departamento save(DepartamentoDTO dto) {
        Departamento d = DepartamentoMapper.toEntity(dto);
        return repo.save(d);
    }

    public void delete(Long id) { repo.deleteById(id); }
}
