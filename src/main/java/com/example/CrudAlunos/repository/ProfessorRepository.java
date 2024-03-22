package com.example.CrudAlunos.repository;

import com.example.CrudAlunos.model.Professor;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
    // Método para buscar um professor pelo ID
    Optional<Professor> findById(Long id);

    // Método para buscar todos os professores
    List<Professor> findAll();

    // Método para salvar um novo professor ou atualizar um existente
    @SuppressWarnings("unchecked")
    Professor save(Professor professor);

    // Método para excluir um professor pelo ID
    void deleteById(Long id);



}
