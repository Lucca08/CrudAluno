package com.example.CrudAlunos.TesteCursoService.TesteExcluirCurso;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.CrudAlunos.model.Curso;
import com.example.CrudAlunos.repository.CursoRepository;
import com.example.CrudAlunos.service.CursoService.ExcluirCurso.ExcluirCursoService;

public class TestExcluiCurso {

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private ExcluirCursoService excluirCursoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExcluirCursoExistente() {
        Long idCurso = 1L;

        Curso cursoExistente = new Curso();
        cursoExistente.setIdDoCurso(idCurso);

        when(cursoRepository.findById(idCurso)).thenReturn(Optional.of(cursoExistente));

        excluirCursoService.excluirCurso(idCurso);

        verify(cursoRepository, times(1)).delete(cursoExistente);
    }

    @Test
    public void testExcluirCursoInexistente() {
        Long idCursoInexistente = 2L;

        when(cursoRepository.findById(idCursoInexistente)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            excluirCursoService.excluirCurso(idCursoInexistente);
        });
    }
}
