package com.example.CrudAlunos.TesteAlunoService.TestVerificaCadastro;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(1L);

        CursoDTO cursoDTO = new CursoDTO();
        cursoDTO.setIdDoCurso(1L);

        Aluno aluno = new Aluno();
        aluno.setId(1L);

        Curso curso = new Curso();
        curso.setIdDoCurso(1L);
        curso.getAlunos().add(aluno);

        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));

        boolean alunoCadastrado = verificarCadastroAlunoService.verificarSeAlunoEstaCadastrado(alunoDTO, cursoDTO);

        assertTrue(alunoCadastrado);
    }
}
