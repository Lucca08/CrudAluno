package com.example.CrudAlunos.TesteCursoService.TesteAtualizarCurso;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.CrudAlunos.dto.CursoDTO;
import com.example.CrudAlunos.model.Curso;
import com.example.CrudAlunos.repository.CursoRepository;
import com.example.CrudAlunos.service.CursoService.AtualizarCurso.AtualizarCursoService;

public class AtualizarCursoServiceTest {

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private AtualizarCursoService atualizarCursoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAtualizarCurso_Success() {

        CursoDTO cursoDTO = new CursoDTO();
        cursoDTO.setIdDoCurso(1L);
        cursoDTO.setNome("Novo Nome do Curso");

        Curso cursoExistente = new Curso();
        cursoExistente.setIdDoCurso(1L);
        cursoExistente.setNome("Nome Antigo do Curso");

        when(cursoRepository.findById(1L)).thenReturn(Optional.of(cursoExistente));

        when(cursoRepository.save(any(Curso.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Curso cursoAtualizado = atualizarCursoService.atualizarCurso(cursoDTO);

        assertNotNull(cursoAtualizado);
        assertEquals(cursoDTO.getIdDoCurso(), cursoAtualizado.getIdDoCurso());
        assertEquals(cursoDTO.getNome(), cursoAtualizado.getNome());
    }

    @Test
    public void testAtualizarCurso_CourseNotFound() {
        CursoDTO cursoDTO = new CursoDTO();
        cursoDTO.setIdDoCurso(1L);
        cursoDTO.setNome("Novo Nome do Curso");

        when(cursoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> atualizarCursoService.atualizarCurso(cursoDTO));
    }
}
