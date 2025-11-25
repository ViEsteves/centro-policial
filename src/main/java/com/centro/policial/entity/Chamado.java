package com.centro.policial.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "chamado")
@Getter @Setter @NoArgsConstructor
public class Chamado {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    private LocalDateTime dataAbertura = LocalDateTime.now();

    private String status;

    private String imagemPath;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;
}
