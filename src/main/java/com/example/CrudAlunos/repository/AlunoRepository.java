package com.example.CrudAlunos.repository;

import com.example.CrudAlunos.dto.AlunoDTO;
import com.example.CrudAlunos.model.Aluno;
import com.example.CrudAlunos.model.Curso;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    List<Aluno> findByNome(String nome); 
    Optional<Aluno> findById(Long id);
    List<Aluno> findByCpf(String cpf);
    List<Aluno> findByMatricula(String matricula);

 
}
