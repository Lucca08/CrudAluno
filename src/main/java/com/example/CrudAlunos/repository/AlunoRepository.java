package com.example.CrudAlunos.repository;

import com.example.CrudAlunos.dto.AlunoDTO;
import com.example.CrudAlunos.model.Aluno;
import com.example.CrudAlunos.model.Curso;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
 // Encontrar aluno por nome
 List<Aluno> findByNome(String nome);
    
 // Encontrar aluno por parte do nome (ignorando maiúsculas e minúsculas)
 List<Aluno> findByNomeContainingIgnoreCase(String parteDoNome);
 
 // Encontrar aluno por parte do nome e por ID do curso
 List<Aluno> findByNomeContainingIgnoreCaseAndCursosId(String parteDoNome, Long idCurso);
 
 // Encontrar aluno por ID do curso
 List<Aluno> findByCursosId(Long idCurso);
}
