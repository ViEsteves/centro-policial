package com.centro.policial.controller.web;

import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.centro.policial.dto.ChamadoDTO;
import com.centro.policial.entity.Chamado;
import com.centro.policial.service.ChamadoService;
import com.centro.policial.service.DepartamentoService;

@Controller
@RequestMapping("/app/chamados")
public class ChamadoWebController {
    private final ChamadoService chamadoService;
    private final DepartamentoService departamentoService;

    public ChamadoWebController(ChamadoService cs, DepartamentoService ds) { this.chamadoService = cs; this.departamentoService = ds; }

    @GetMapping
    public String list(@RequestParam(defaultValue="0") int page,
                       @RequestParam(defaultValue="10") int size,
                       @RequestParam(required=false) String filtro,
                       Model model) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("dataAbertura").descending());
        var pageList = chamadoService.list(filtro, pageable);
        model.addAttribute("page", pageList);
        model.addAttribute("filtro", filtro);
        return "chamado/list";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("chamado", new ChamadoDTO());
        model.addAttribute("departamentos", departamentoService.list(null, PageRequest.of(0, 100)).getContent());
        return "chamado/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("chamado") ChamadoDTO dto, BindingResult br,
                         @RequestParam("imagem") MultipartFile imagem, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("departamentos", departamentoService.list(null, PageRequest.of(0, 100)).getContent());
            return "chamado/form";
        }
        chamadoService.save(dto, imagem);
        return "redirect:/app/chamados";
    }

    @GetMapping("/detalhe/{id}")
    public String detalhe(@PathVariable Long id, Model model) {
        Chamado c = chamadoService.get(id);
        model.addAttribute("chamado", c);
        return "chamado/detail";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        chamadoService.delete(id);
        return "redirect:/app/chamados";
    }
}
