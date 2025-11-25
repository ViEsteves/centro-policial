package com.centro.policial.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.centro.policial.entity.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    Page<Departamento> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
