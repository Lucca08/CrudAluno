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

    public Curso criarCurso(CursoDTO cursoDTO) {
        if (cursoDTO.getDescricao() == null || cursoDTO.getDescricao().isEmpty()) {
            throw new IllegalArgumentException("Descrição do curso é obrigatória.");
        }

        if (cursoDTO.getProfessor() != null) {
            if (cursoDTO.getProfessor().getId() != null) {
                Professor professor = professorRepository.findById(cursoDTO.getProfessor().getId())
                        .orElseThrow(() -> new RuntimeException(
                                "Professor não encontrado com ID: " + cursoDTO.getProfessor().getId()));
                return criarCursoComProfessor(cursoDTO, professor);
            } else {
                Professor novoProfessor = new Professor();
                novoProfessor.setNome(cursoDTO.getProfessor().getNome());
                Professor professorSalvo = professorRepository.save(novoProfessor);
                return criarCursoComProfessor(cursoDTO, professorSalvo);
            }
        } else {
            throw new RuntimeException("Professor não fornecido");
        }
    }

    private Curso criarCursoComProfessor(CursoDTO cursoDTO, Professor professor) {
        Curso curso = new Curso();
        curso.setId(cursoDTO.getId());
        curso.setNome(cursoDTO.getNome());
        curso.setProfessor(professor);
        curso.setDescricao(cursoDTO.getDescricao());
        

        cursoRepository.save(curso);

        logger.info("Curso criado com sucesso: " + curso.getNome());

        return curso;
    }
}
