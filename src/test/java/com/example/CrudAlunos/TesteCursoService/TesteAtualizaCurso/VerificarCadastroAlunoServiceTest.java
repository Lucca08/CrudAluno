package com.example.CrudAlunos.TesteCursoService.TesteAtualizaCurso;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.CrudAlunos.dto.AlunoDTO;
import com.example.CrudAlunos.dto.CursoDTO;
import com.example.CrudAlunos.model.Aluno;
import com.example.CrudAlunos.model.Curso;
import com.example.CrudAlunos.repository.AlunoRepository;
import com.example.CrudAlunos.repository.CursoRepository;
import com.example.CrudAlunos.service.AlunoService.VerificarCadastroAluno.VerificarCadastroAlunoService;

public class VerificarCadastroAlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private VerificarCadastroAlunoService verificarCadastroAlunoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testVerificarSeAlunoEstaCadastrado() {

        Aluno aluno = mock(Aluno.class);
        when(aluno.getId()).thenReturn(1L);

        Curso curso = mock(Curso.class);
        when(curso.getId()).thenReturn(1L);
        when(curso.getAlunos()).thenReturn(new ArrayList<>());
        curso.getAlunos().add(aluno);

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(1L);

        CursoDTO cursoDTO = new CursoDTO();
        cursoDTO.setId(1L);

        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));

        boolean alunoCadastrado = verificarCadastroAlunoService.verificarSeAlunoEstaCadastrado(alunoDTO, cursoDTO);

        assertTrue(alunoCadastrado);
    }
}
