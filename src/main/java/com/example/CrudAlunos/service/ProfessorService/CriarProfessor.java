package com.example.CrudAlunos.service.ProfessorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CrudAlunos.dto.ProfessorDTO;
import com.example.CrudAlunos.model.Professor;
import com.example.CrudAlunos.repository.ProfessorRepository;

@Service
public class CriarProfessor {

    @Autowired
    private ProfessorRepository professorRepository;

    public Professor criarProfessor(ProfessorDTO professorDTO) {
        Professor professor = new Professor();
        professor.setNome(professorDTO.getNome());
        return professorRepository.save(professor);
    }
}
