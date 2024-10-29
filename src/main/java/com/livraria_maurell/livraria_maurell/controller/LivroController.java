package com.livraria_maurell.livraria_maurell.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.livraria_maurell.livraria_maurell.model.Livro;
import com.livraria_maurell.livraria_maurell.repository.LivroRepository;

@Controller
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private LivroRepository livroRepository;

   @GetMapping
    public String listarLivros(@RequestParam(value = "search", required = false) String search, Model model) {
        List<Livro> livros;
        if (search != null && !search.isEmpty()) {
            livros = livroRepository.findByTituloContainingIgnoreCaseOrAutorContainingIgnoreCase(search, search);
        } else {
            livros = livroRepository.findAll();
        }
        model.addAttribute("livros", livros);
          // Retornar a lista como parte da resposta HTML
          return "livros/listar";
    }

    @GetMapping("/novo")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("livro", new Livro());
        return "livros/formulario"; // Retorna o formulário para adicionar um novo livro
    }

    @PostMapping
    public String salvarLivro(@ModelAttribute Livro livro) {
        livroRepository.save(livro);
        return "redirect:/livros";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable Long id, Model model) {
        Livro livro = livroRepository.findById(id).orElseThrow();
        model.addAttribute("livro", livro);
        return "livros/formulario";
    }

    @PostMapping("/editar/{id}")
    public String atualizarLivro(@PathVariable Long id, @ModelAttribute Livro livro) {
        livro.setId(id);
        livroRepository.save(livro);
        return "redirect:/livros";
    }

    @GetMapping("/deletar/{id}")
    public String deletarLivro(@PathVariable Long id) {
        livroRepository.deleteById(id);
        return "redirect:/livros";
    }
}