package com.example.CrudAlunos.TestesParaApresentacao;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.CrudAlunos.model.Aluno;
import com.example.CrudAlunos.model.Curso;
import com.example.CrudAlunos.repository.AlunoRepository;
import com.example.CrudAlunos.repository.CursoRepository;
import com.example.CrudAlunos.service.AlunoService.AlunoService;


public class TesteVericarAlunoNoCurso {
    
    @InjectMocks
    private AlunoService alunoService;

    @Mock
    private AlunoRepository alunoRepository;

    @Mock
    private CursoRepository cursoRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Testar verificar aluno no curso_COM SUCESSO")
    public void testVerificarAlunoNoCurso(){
        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNome("Joao");
        aluno.setCpf("12345678900");
        aluno.setMatricula("20240001");

        Curso curso = new Curso();
        curso.setIdDoCurso(1L);
        curso.setNome("Curso de Teste");
        curso.getAlunos().add(aluno);

        
        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));

        boolean alunoNoCurso = alunoService.verificarAlunoNoCurso(1L, 1L);

        assertTrue(alunoNoCurso);

    }

    @Test
    @DisplayName("Testar verificar aluno no curso_SEM SUCESSO")
    public void testVerificarAlunoNoCursoSemSucesso(){
        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNome("João");
        aluno.setCpf("12345678900");
        aluno.setMatricula("20240001");

        Curso curso = new Curso();
        curso.setIdDoCurso(1L);
        curso.setNome("Curso Teste");

        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));

        boolean alunoNoCurso = alunoService.verificarAlunoNoCurso(1L, 1L);

        assertFalse(alunoNoCurso);

    }

    

   













}
