package com.example.CrudAlunos.repository;

import com.example.CrudAlunos.model.Aluno;
import com.example.CrudAlunos.model.Curso;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    void cadastrarAlunoNoCurso(Long idAluno, Long idCurso);
    void sairDoCurso(Long idAluno, Long idCurso);
    boolean alunoEstaCadastradoNoCurso(Long idAluno, Long idCurso);
    void excluirAluno(Long idAluno);
    void atualizarAluno(Aluno aluno);
    List<Aluno> findByCursos(Curso curso);
}
