package com.example.CrudAlunos.TesteAlunoService.TestCadastroAluno;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.CrudAlunos.dto.AlunoDTO;
import com.example.CrudAlunos.model.Aluno;
import com.example.CrudAlunos.model.Curso;
import com.example.CrudAlunos.repository.AlunoRepository;
import com.example.CrudAlunos.repository.CursoRepository;
import com.example.CrudAlunos.service.AlunoService.CadastrarAluno.CadastrarAlunoService;
import com.example.CrudAlunos.Stub.StubAlunos;
import com.example.CrudAlunos.Stub.StubCurso;

public class CadastrarAlunoServiceTest {
    private CursoRepository cursoRepository;
    private AlunoRepository alunoRepository;
    private CadastrarAlunoService cadastrarAlunoService;

    @BeforeEach
    public void setUp() {
        cursoRepository = mock(CursoRepository.class);
        alunoRepository = mock(AlunoRepository.class);
        cadastrarAlunoService = new CadastrarAlunoService(alunoRepository, cursoRepository);
    }

    @Test
    public void testCadastrarAlunoNoCurso_Success() throws Exception {
        // Mock do DTO do aluno
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setNome("João");

        // Mock do curso
        Curso curso = StubCurso.createCursoStub();

        // Mock do retorno do repositório ao buscar o curso pelo ID
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));

        // Mock do aluno salvo no repositório
        Aluno alunoSalvo = StubAlunos.AlunoStub1();
        when(alunoRepository.save(any(Aluno.class))).thenReturn(alunoSalvo);

        // Adiciona um curso à lista de cursos do aluno
        alunoSalvo.getCursos().add(curso);

        // Executar o serviço de cadastrar aluno no curso
        Aluno alunoCadastrado = cadastrarAlunoService.cadastrarAlunoNoCurso(alunoDTO, 1L);

        // Verificar se o aluno foi cadastrado no curso
        assertEquals(StubAlunos.AlunoStub1().getNome(), alunoCadastrado.getNome());
        assertEquals(curso.getId(), alunoCadastrado.getCursos().get(0).getId());
    }

    @Test
    public void testCadastrarAlunoNoCurso_CourseNotFound() {
        // Mock do DTO do aluno
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setNome("Maria");

        // Mock do retorno do repositório ao buscar o curso pelo ID
        when(cursoRepository.findById(1L)).thenReturn(Optional.empty());

        // Executar o serviço de cadastrar aluno no curso e verificar se uma exceção é lançada
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(Exception.class, () -> {
            cadastrarAlunoService.cadastrarAlunoNoCurso(alunoDTO, 1L);
        });

        // Verificar se a mensagem de exceção está correta
        assertEquals("Curso não encontrado", exception.getMessage());
    }
}
