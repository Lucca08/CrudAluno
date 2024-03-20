package com.example.CrudAlunos.service.CursoService.AtualizarCurso;

import java.util.Optional;
import java.util.logging.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CrudAlunos.dto.CursoDTO;
import com.example.CrudAlunos.model.Curso;
import com.example.CrudAlunos.repository.CursoRepository;

@Service
public class AtualizarCursoService {
    private static final Logger logger = Logger.getLogger(AtualizarCursoService.class.getName());


    @Autowired
    private CursoRepository cursoRepository;

    public Curso atualizarCurso(CursoDTO cursoDTO) {
        Optional<Curso> optionalCurso = cursoRepository.findById(cursoDTO.getId());
        
        if (optionalCurso.isPresent()) {
            Curso curso = optionalCurso.get();
            curso.setNome(cursoDTO.getNome());

            logger.info("Curso atualizado: " + curso);
            
            return cursoRepository.save(curso);
        } else {
            logger.warning("Curso não encontrado com o ID: " + cursoDTO.getId());
            throw new RuntimeException("Curso não encontrado com o ID: " + cursoDTO.getId());
        }
    }
}
