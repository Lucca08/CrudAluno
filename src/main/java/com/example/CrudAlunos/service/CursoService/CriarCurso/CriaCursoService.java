package com.example.CrudAlunos.service.CursoService.CriarCurso;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CrudAlunos.dto.CursoDTO;
import com.example.CrudAlunos.model.Curso;
import com.example.CrudAlunos.model.Professor;
import com.example.CrudAlunos.repository.CursoRepository;
import com.example.CrudAlunos.repository.ProfessorRepository;

@Service
public class CriaCursoService {

    private static final Logger logger = Logger.getLogger(CriaCursoService.class.getName());

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    public Curso criarCurso(CursoDTO cursoDTO, Long idProfessor) {
        Professor professor = professorRepository.findById(idProfessor)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado com ID: " + idProfessor));

        Curso curso = new Curso();
        curso.setNome(cursoDTO.getNome());
        curso.setProfessor(professor); 

        logger.info("Criando curso com os dados: " + cursoDTO);
        return cursoRepository.save(curso);
    }
}
