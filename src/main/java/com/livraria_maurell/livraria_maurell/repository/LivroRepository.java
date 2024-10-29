package com.livraria_maurell.livraria_maurell.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livraria_maurell.livraria_maurell.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByTituloContainingIgnoreCaseOrAutorContainingIgnoreCase(String titulo, String autor);
}