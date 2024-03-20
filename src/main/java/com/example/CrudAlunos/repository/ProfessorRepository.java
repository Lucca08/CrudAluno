package com.example.CrudAlunos.repository;

import com.example.CrudAlunos.model.Professor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
    void salvarProfessor(Professor professor);
    void atualizarProfessor(Professor professor);
    void excluirProfessor(Long idProfessor);
    Professor encontrarProfessorPorId(Long idProfessor);
    List<Professor> encontrarTodosOsProfessores();


}
