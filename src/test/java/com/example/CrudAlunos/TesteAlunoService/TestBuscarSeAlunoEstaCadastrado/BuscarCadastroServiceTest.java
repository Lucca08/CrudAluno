package com.example.CrudAlunos.TesteAlunoService.TestBuscarSeAlunoEstaCadastrado;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.CrudAlunos.dto.AlunoDTO;
import com.example.CrudAlunos.model.Aluno;
import com.example.CrudAlunos.model.Curso;
import com.example.CrudAlunos.repository.AlunoRepository;
import com.example.CrudAlunos.service.AlunoService.BuscarSeAlunoEstaCadastrado.BuscarCadastroService;
import com.example.CrudAlunos.Stub.StubAlunos;
import com.example.CrudAlunos.Stub.StubCurso;

public class BuscarCadastroServiceTest {

    private AlunoRepository alunoRepository;
    private BuscarCadastroService buscarCadastroService;

    @BeforeEach
    public void setUp() {
        alunoRepository = mock(AlunoRepository.class);
        buscarCadastroService = new BuscarCadastroService(alunoRepository);
    }

    @Test
    public void testVerificarAlunoNoCurso_AlunoPresenteNoCurso() {
        Aluno aluno = StubAlunos.AlunoStub1();

        Curso curso = StubCurso.createCursoStub();

        // Adicionar o curso ao aluno
        aluno.getCursos().add(curso);

        // Simular comportamento do repositório ao buscar pelo aluno
        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));

        // Verificar se o aluno está cadastrado no curso com ID 1
        assertTrue(buscarCadastroService.verificarAlunoNoCurso(new AlunoDTO(1L), 1L));
    }

    @Test
    public void testVerificarAlunoNoCurso_AlunoNaoPresenteNoCurso() {
        // Criar um aluno stub com ID 1
        Aluno aluno = StubAlunos.AlunoStub1();

        // Criar um curso stub com ID 1
        Curso curso = StubCurso.createCursoStub();

        // Simular comportamento do repositório ao buscar pelo aluno
        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));

        // Verificar se o aluno está cadastrado no curso com ID 2
        assertFalse(buscarCadastroService.verificarAlunoNoCurso(new AlunoDTO(1L), 2L));
    }

    @Test
    public void testVerificarAlunoNoCurso_AlunoNaoEncontrado() {
        // Simular comportamento do repositório ao buscar pelo aluno
        when(alunoRepository.findById(1L)).thenReturn(Optional.empty());

        // Verificar se o aluno está cadastrado no curso com ID 1
        assertFalse(buscarCadastroService.verificarAlunoNoCurso(new AlunoDTO(1L), 1L));
    }
}
