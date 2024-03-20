package com.example.CrudAlunos.service.CursoService.VerTodosOsAlunosDoCurso;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CrudAlunos.dto.AlunoDTO;
import com.example.CrudAlunos.model.Curso;
import com.example.CrudAlunos.repository.CursoRepository;

@Service
public class VerAlunosDoCursoService {

    private static final Logger logger = Logger.getLogger(VerAlunosDoCursoService.class.getName());

    @Autowired
    private CursoRepository cursoRepository;

    public List<AlunoDTO> verAlunosDoCurso(Long idCurso) {
        Curso curso = cursoRepository.findById(idCurso).orElseThrow(() -> new RuntimeException("Curso não encontrado com ID: " + idCurso));

        List<AlunoDTO> alunosDoCurso = curso.getAlunos().stream()
                                                .map(AlunoDTO::new)
                                                .collect(Collectors.toList());
        
        logger.info("Alunos do curso " + curso.getNome() + ": " + alunosDoCurso.size());
        
        return alunosDoCurso;
    }
   
}

