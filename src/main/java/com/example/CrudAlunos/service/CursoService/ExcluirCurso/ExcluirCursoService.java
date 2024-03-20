package com.example.CrudAlunos.service.CursoService.ExcluirCurso;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CrudAlunos.model.Curso;
import com.example.CrudAlunos.repository.CursoRepository;

@Service
public class ExcluirCursoService {

    private static final Logger logger = Logger.getLogger(ExcluirCursoService.class.getName());

    @Autowired
    private CursoRepository cursoRepository;

    public void excluirCurso(Long idCurso) {
        Optional<Curso> optionalCurso = cursoRepository.findById(idCurso);
        
        if (optionalCurso.isPresent()) {
            Curso curso = optionalCurso.get();
            cursoRepository.delete(curso);
            logger.info("Curso excluído com sucesso: " + curso);
        } else {
            logger.warning("Não foi possível encontrar o curso com o ID: " + idCurso);
            throw new RuntimeException("Não foi possível encontrar o curso com o ID: " + idCurso);
        }
    }
}
