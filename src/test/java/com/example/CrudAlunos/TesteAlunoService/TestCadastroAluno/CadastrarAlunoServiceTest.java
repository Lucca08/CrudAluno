package com.example.CrudAlunos.TesteAlunoService.TestCadastroAluno;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.CrudAlunos.dto.AlunoDTO;
import com.example.CrudAlunos.dto.CursoDTO;
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
        cadastrarAlunoService = new CadastrarAlunoService();
        cadastrarAlunoService.setAlunoRepository(alunoRepository);
        cadastrarAlunoService.setCursoRepository(cursoRepository);
    }

    @Test
    public void testCadastrarAluno_Success() throws Exception {
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setNome("Lucca");
        alunoDTO.setCurso(new CursoDTO(1L, "Curso de Teste", null, null)); 
        Curso curso = StubCurso.createCursoStub();

        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));

        Aluno alunoSalvo = StubAlunos.AlunoStub1();
        when(alunoRepository.save(any(Aluno.class))).thenReturn(alunoSalvo);

        alunoSalvo.getCursos().add(curso);

        Aluno alunoCadastrado = cadastrarAlunoService.cadastrarAluno(alunoDTO);

        assertEquals(StubAlunos.AlunoStub1().getNome(), alunoCadastrado.getNome());
        assertEquals(curso.getIdDoCurso(), alunoCadastrado.getCursos().get(0).getIdDoCurso());
    }

    @Test
    public void testCadastrarAluno_CourseNotFound() {
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setNome("Maria");
        alunoDTO.setCurso(new CursoDTO(1L, "Curso de Teste", null, null)); // Simulate DTO with course ID

        when(cursoRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            cadastrarAlunoService.cadastrarAluno(alunoDTO);
        });

        assertEquals("Curso não encontrado com o ID: 1", exception.getMessage());
    }

    @Test
    public void testCadastrarAluno_AlreadyEnrolled() {
        // Configurar DTO do aluno
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setNome("Maria");
        alunoDTO.setCurso(new CursoDTO(1L, "Curso de Teste", null, null)); // Simular DTO com ID do curso
    
        // Simular o curso sendo encontrado pelo repositório
        Curso curso = StubCurso.createCursoStub();
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));
    
        // Simular um aluno já matriculado no curso
        Aluno alunoJaMatriculado = new Aluno();
        alunoJaMatriculado.setNome("João");
        alunoJaMatriculado.getCursos().add(curso);
    
        // Simular a resposta do repositório ao verificar se o aluno já está matriculado no curso
        when(alunoRepository.existsAlunoByCursosContains(curso)).thenReturn(true);
    
        // Executar o método e esperar uma exceção ser lançada
        Exception exception = assertThrows(RuntimeException.class, () -> {
            cadastrarAlunoService.cadastrarAluno(alunoDTO);
        });
    
        // Verificar se a mensagem de exceção esperada é lançada
        assertEquals("Aluno já matriculado no curso", exception.getMessage());
    }
    
}
    