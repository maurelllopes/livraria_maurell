package com.livraria_maurell.livraria_maurell.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livraria_maurell.livraria_maurell.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
}