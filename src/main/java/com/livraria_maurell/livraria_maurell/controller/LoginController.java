package com.livraria_maurell.livraria_maurell.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.livraria_maurell.livraria_maurell.service.UsuarioService;

@Controller
public class LoginController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String login() {
        return "login"; // página de login
    }

    @PostMapping("/login")
    public String autenticar(String username, String password, Model model) {
        if (usuarioService.autenticar(username, password)) {
            return "redirect:/livros"; // redireciona para a lista de livros
        } else {
            model.addAttribute("error", "Usuário ou senha inválidos!");
            return "login"; // retorna à página de login com mensagem de erro
        }
    }
}
