package com.example.CrudAlunos.repository;

import com.example.CrudAlunos.model.Aluno;
import com.example.CrudAlunos.model.Curso;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    void criarCurso(Curso curso);
    void atualizarCurso(Curso curso);
    void excluirCurso(Long idCurso);
    List<Aluno> findByCurso(Long idCurso);
    List<Curso> findALLCursos();
    Optional<Curso> findById(Long idCurso);
    
}
