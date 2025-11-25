package com.centro.policial.service;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.centro.policial.dto.ChamadoDTO;
import com.centro.policial.entity.Chamado;
import com.centro.policial.entity.Departamento;
import com.centro.policial.mapper.ChamadoMapper;
import com.centro.policial.repository.ChamadoRepository;
import com.centro.policial.repository.DepartamentoRepository;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class ChamadoService {
    private final ChamadoRepository chamadoRepo;
    private final DepartamentoRepository departamentoRepo;
    private final String uploadDir;

    public ChamadoService(ChamadoRepository cr, DepartamentoRepository dr, org.springframework.core.env.Environment env) {
        this.chamadoRepo = cr;
        this.departamentoRepo = dr;
        this.uploadDir = env.getProperty("app.upload.dir", "uploads");
        try { Files.createDirectories(Paths.get(uploadDir)); } catch (IOException e) { /* ignore */ }
    }

    public Page<Chamado> list(String filtro, Pageable pageable) {
        if (filtro == null || filtro.isBlank()) return chamadoRepo.findAll(pageable);
        return chamadoRepo.findByTituloContainingIgnoreCase(filtro, pageable);
    }

    public Chamado get(Long id) { return chamadoRepo.findById(id).orElseThrow(); }

    public Chamado save(ChamadoDTO dto, MultipartFile file) {
        Departamento dept = departamentoRepo.findById(dto.getDepartamentoId()).orElseThrow();
        Chamado c = ChamadoMapper.toEntity(dto, dept);
        if (file != null && !file.isEmpty()) {
            String filename = UUID.randomUUID().toString() + "_" + Path.of(file.getOriginalFilename()).getFileName().toString();
            Path target = Paths.get(uploadDir).resolve(filename);
            try {
                Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
                c.setImagemPath(filename);
            } catch (IOException e) { throw new RuntimeException("Falha ao salvar arquivo", e); }
        }
        return chamadoRepo.save(c);
    }

    public void delete(Long id) { chamadoRepo.deleteById(id); }
}
