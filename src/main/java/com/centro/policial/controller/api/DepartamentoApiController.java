package com.centro.policial.controller.api;

import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cent...policial.dto.DepartamentoDTO; // <-- replace double-check package if needed
import com.centro.policial.entity.Departamento;
import com.centro.policial.service.DepartamentoService;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoApiController {
    private final DepartamentoService service;
    public DepartamentoApiController(DepartamentoService service) { this.service = service; }

    @GetMapping
    public Page<Departamento> list(@RequestParam(defaultValue="0") int page,
                                   @RequestParam(defaultValue="10") int size,
                                   @RequestParam(required=false) String filtro,
                                   @RequestParam(defaultValue="nome,asc") String sort) {
        Sort s = Sort.by(sort.split(",")[0]);
        if (sort.split(",").length>1 && sort.split(",")[1].equalsIgnoreCase("desc")) s = s.descending();
        return service.list(filtro, PageRequest.of(page, size, s));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departamento> get(@PathVariable Long id) { return ResponseEntity.ok(service.get(id)); }

    @PostMapping
    public ResponseEntity<Departamento> create(@RequestBody DepartamentoDTO dto) { return ResponseEntity.ok(service.save(dto)); }

    @PutMapping("/{id}")
    public ResponseEntity<Departamento> update(@PathVariable Long id, @RequestBody DepartamentoDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(service.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { service.delete(id); return ResponseEntity.noContent().build(); }
}
