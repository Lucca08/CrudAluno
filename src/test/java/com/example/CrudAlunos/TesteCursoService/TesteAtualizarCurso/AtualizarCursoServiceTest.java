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

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAtualizarCurso() {
        CursoDTO cursoDTO = new CursoDTO();
        cursoDTO.setId(1L);
        cursoDTO.setNome("Novo Nome do Curso");

        Curso curso = new Curso();
        curso.setId(1L);
        curso.setNome("Nome Antigo do Curso");

        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));
        when(cursoRepository.save(any(Curso.class))).thenAnswer(invocation -> {
            Curso cursoSalvo = invocation.getArgument(0);
            return cursoSalvo;
        });

        Curso cursoAtualizado = atualizarCursoService.atualizarCurso(cursoDTO);

        assertEquals(cursoDTO.getNome(), cursoAtualizado.getNome());
    }
}
