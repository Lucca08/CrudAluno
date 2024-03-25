package com.example.CrudAlunos.TesteCursoService.TesteVerTodosOsCursos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.CrudAlunos.dto.CursoDTO;
import com.example.CrudAlunos.model.Curso;
import com.example.CrudAlunos.model.Professor;
import com.example.CrudAlunos.repository.CursoRepository;
import com.example.CrudAlunos.service.CursoService.VerTodosOsCursos.VerTodosOsCursos;

public class TesteVerTodosOsCursos {

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private VerTodosOsCursos verTodosOsCursos;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListarTodosCursos() {
        Curso curso1 = new Curso();
        curso1.setId(1L);
        curso1.setNome("Curso 1");

        Curso curso2 = new Curso();
        curso2.setId(2L);
        curso2.setNome("Curso 2");

        Professor professor1 = new Professor();
        professor1.setId(1L);
        professor1.setNome("Professor 1");

        Professor professor2 = new Professor();
        professor2.setId(2L);
        professor2.setNome("Professor 2");

        curso1.setProfessor(professor1);
        curso2.setProfessor(professor2);

        List<Curso> cursos = new ArrayList<>();
        cursos.add(curso1);
        cursos.add(curso2);

        when(cursoRepository.findAll()).thenReturn(cursos);

        List<CursoDTO> cursosDTO = verTodosOsCursos.listarTodosCursos();

        assertEquals(cursos.size(), cursosDTO.size());
        assertEquals(curso1.getNome(), cursosDTO.get(0).getNome());
        assertEquals(curso1.getProfessor().getNome(), cursosDTO.get(0).getProfessor().getNome());
        assertEquals(curso2.getNome(), cursosDTO.get(1).getNome());
        assertEquals(curso2.getProfessor().getNome(), cursosDTO.get(1).getProfessor().getNome());
    }
}
