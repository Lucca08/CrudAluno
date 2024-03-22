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
        Curso curso = cursoRepository.findById(cursoDTO.getId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        curso.setNome(cursoDTO.getNome());

        logger.info("Atualizando curso: " + curso.getNome());

        return cursoRepository.save(curso);
    }
}
