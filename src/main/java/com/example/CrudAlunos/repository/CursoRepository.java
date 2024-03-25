package com.example.CrudAlunos.repository;

import com.example.CrudAlunos.model.Aluno;
import com.example.CrudAlunos.model.Curso;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findByNome(String nome);

    List<Curso> findByProfessorIdDoProfessor(Long idDoProfessor);

    List<Curso> findByAlunosId(Long idAluno);

    
}
