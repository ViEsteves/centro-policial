package com.centro.policial.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.centro.policial.entity.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
    Page<Chamado> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);
}
