package com.example.CrudAlunos.repository;

import com.example.CrudAlunos.model.Professor;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
   Optional<Professor> findById(Long id);

    List<Professor> findAll();

    @SuppressWarnings("unchecked")
    Professor save(Professor professor);

    void deleteById(Long id);



}
