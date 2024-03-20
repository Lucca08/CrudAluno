package com.example.CrudAlunos.repository;

import com.example.CrudAlunos.model.Professor;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
    void salvarProfessor(Professor professor);
    void atualizarProfessor(Professor professor);
    void excluirProfessor(Long idProfessor);
    public Optional<Professor> findById(Long id);
    List<Professor> encontrarTodosOsProfessores();
    boolean professorEstaCadastrado(Long idProfessor);




}
