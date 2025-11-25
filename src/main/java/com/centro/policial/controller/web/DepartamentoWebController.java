package com.centro.policial.controller.web;

import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.centro.policial.dto.DepartamentoDTO;
import com.centro.policial.entity.Departamento;
import com.centro.policial.service.DepartamentoService;

@Controller
@RequestMapping("/app/departamentos")
public class DepartamentoWebController {
    private final DepartamentoService service;
    public DepartamentoWebController(DepartamentoService service) { this.service = service; }

    @GetMapping
    public String list(@RequestParam(defaultValue="0") int page,
                       @RequestParam(defaultValue="10") int size,
                       @RequestParam(required=false) String filtro,
                       Model model) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("nome"));
        Page<Departamento> pageList = service.list(filtro, pageable);
        model.addAttribute("page", pageList);
        model.addAttribute("filtro", filtro);
        return "departamento/list";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("departamento", new DepartamentoDTO());
        return "departamento/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("departamento") DepartamentoDTO dto, BindingResult br) {
        if (br.hasErrors()) return "departamento/form";
        service.save(dto);
        return "redirect:/app/departamentos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Departamento d = service.get(id);
        DepartamentoDTO dto = new DepartamentoDTO();
        dto.setId(d.getId()); dto.setNome(d.getNome()); dto.setDescricao(d.getDescricao());
        model.addAttribute("departamento", dto);
        return "departamento/form";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/app/departamentos";
    }
}
