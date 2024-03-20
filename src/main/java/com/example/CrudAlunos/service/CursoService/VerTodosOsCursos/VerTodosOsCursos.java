
package com.example.CrudAlunos.service.CursoService.VerTodosOsCursos;

import java.util.List;
import java.util.stream.Collectors;
import java.util.logging.Logger; 


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CrudAlunos.dto.CursoDTO;
import com.example.CrudAlunos.model.Curso;
import com.example.CrudAlunos.repository.CursoRepository;

@Service
public class VerTodosOsCursos {

    @Autowired
    private CursoRepository cursoRepository;
    
    private static final Logger logger = Logger.getLogger(VerTodosOsCursos.class.getName());


    public List<CursoDTO> listarTodosCursos() {
        List<Curso> cursos = cursoRepository.findAll();
        logger.info("Listando todos os cursos: " + cursos.size() + " encontrados");

        return cursos.stream()
                     .map(CursoDTO::new)
                     .collect(Collectors.toList());
    }
}
