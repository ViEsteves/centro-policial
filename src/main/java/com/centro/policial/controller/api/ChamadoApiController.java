package com.centro.policial.controller.api;

import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.centro.policial.dto.ChamadoDTO;
import com.centro.policial.entity.Chamado;
import com.centro.policial.service.ChamadoService;

@RestController
@RequestMapping("/api/chamados")
public class ChamadoApiController {
    private final ChamadoService service;
    public ChamadoApiController(ChamadoService service) { this.service = service; }

    @GetMapping
    public Page<Chamado> list(@RequestParam(defaultValue="0") int page,
                              @RequestParam(defaultValue="10") int size,
                              @RequestParam(required=false) String filtro,
                              @RequestParam(defaultValue="dataAbertura,desc") String sort) {
        Sort s = Sort.by(sort.split(",")[0]);
        if (sort.split(",").length>1 && sort.split(",")[1].equalsIgnoreCase("desc")) s = s.descending();
        else s = s.ascending();
        return service.list(filtro, PageRequest.of(page, size, s));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chamado> get(@PathVariable Long id) { return ResponseEntity.ok(service.get(id)); }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Chamado> create(@ModelAttribute ChamadoDTO dto, @RequestPart(required=false) MultipartFile imagem) {
        return ResponseEntity.ok(service.save(dto, imagem));
    }

    @PutMapping(value="/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<Chamado> update(@PathVariable Long id, @ModelAttribute ChamadoDTO dto, @RequestPart(required=false) MultipartFile imagem) {
        dto.setId(id);
        return ResponseEntity.ok(service.save(dto, imagem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { service.delete(id); return ResponseEntity.noContent().build(); }
}
