package com.example.CrudAlunos.TesteAlunoService;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.CrudAlunos.dto.AlunoDTO;
import com.example.CrudAlunos.model.Aluno;
import com.example.CrudAlunos.model.Curso;
import com.example.CrudAlunos.repository.AlunoRepository;
import com.example.CrudAlunos.repository.CursoRepository;
import com.example.CrudAlunos.service.AlunoService.AlunoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class AlunoServiceTest {

    @InjectMocks
    private AlunoService alunoService;

    @Mock
    private AlunoRepository alunoRepository;

    @Mock
    private CursoRepository cursoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Testar cadastrar aluno")
    public void testCadastrarAluno() {
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setNome("João");
        alunoDTO.setCpf("12345678900");
        alunoDTO.setMatricula("20240001");

        Aluno alunoSalvo = new Aluno();
        alunoSalvo.setId(1L);
        alunoSalvo.setNome("João");
        alunoSalvo.setCpf("12345678900");
        alunoSalvo.setMatricula("20240001");

        when(alunoRepository.save(any(Aluno.class))).thenReturn(alunoSalvo);

        Aluno alunoRetornado = alunoService.cadastrarAluno(alunoDTO);

        assertNotNull(alunoRetornado);
        assertEquals(alunoSalvo.getId(), alunoRetornado.getId());
        assertEquals(alunoSalvo.getNome(), alunoRetornado.getNome());
        assertEquals(alunoSalvo.getCpf(), alunoRetornado.getCpf());
        assertEquals(alunoSalvo.getMatricula(), alunoRetornado.getMatricula());
    }

    @Test
    @DisplayName("Testar atualizar aluno")
    public void testAtualizarAluno() {
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(1L); 
        alunoDTO.setNome("João");
        alunoDTO.setCpf("12345678900");
        alunoDTO.setMatricula("20240001");
    
        Aluno alunoAtualizado = new Aluno();
        alunoAtualizado.setId(1L);
        alunoAtualizado.setNome("João da Silva");
        alunoAtualizado.setCpf("12345678900");
        alunoAtualizado.setMatricula("20240001");
    
        when(alunoRepository.findById(1L)).thenReturn(Optional.of(alunoAtualizado));
        when(alunoRepository.save(any(Aluno.class))).thenReturn(alunoAtualizado);
    
        Aluno alunoRetornado = alunoService.atualizarAluno(alunoDTO);
    
        assertNotNull(alunoRetornado);
        assertEquals(alunoAtualizado.getId(), alunoRetornado.getId());
        assertEquals(alunoAtualizado.getNome(), alunoRetornado.getNome());
        assertEquals(alunoAtualizado.getCpf(), alunoRetornado.getCpf());
        assertEquals(alunoAtualizado.getMatricula(), alunoRetornado.getMatricula());
    }
    
    

    @Test
    @DisplayName("testar deletar aluno")
    public void testDeletarAluno() {
        Aluno alunoExistente = new Aluno();
        alunoExistente.setId(1L);
        alunoExistente.setNome("João");
        alunoExistente.setCpf("12345678900");
        alunoExistente.setMatricula("20240001");

        when(alunoRepository.findById(1L)).thenReturn(Optional.of(alunoExistente));
        doNothing().when(alunoRepository).deleteById(1L);

        Aluno alunoDeletado = alunoService.deletarAluno(1L);

        assertNotNull(alunoDeletado);
        assertEquals(alunoExistente.getId(), alunoDeletado.getId());
        assertEquals(alunoExistente.getNome(), alunoDeletado.getNome());
    }

    @Test
    @DisplayName("Testar sair do curso")
    public void testSairDoCurso() {
        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNome("João");
        aluno.setCpf("12345678900");
        aluno.setMatricula("20240001");

        Curso curso = new Curso();
        curso.setIdDoCurso(1L);
        curso.setNome("Curso Teste");
        curso.getAlunos().add(aluno);

        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));

        alunoService.sairDoCurso(1L, 1L);

        assertFalse(curso.getAlunos().contains(aluno));
    }

    @Test
    @DisplayName("Testar verificar aluno no curso")
    public void testVerificarAlunoNoCurso(){
        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNome("João");
        aluno.setCpf("12345678900");
        aluno.setMatricula("20240001");

        Curso curso = new Curso();
        curso.setIdDoCurso(1L);
        curso.setNome("Curso Teste");
        curso.getAlunos().add(aluno);

        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));

        boolean alunoNoCurso = alunoService.verificarAlunoNoCurso(1L, 1L);

        assertTrue(alunoNoCurso);

    }

    @Test
    @DisplayName("Testar verificar aluno no curso - Aluno cadastrado")
    public void testVerificarSeAlunoEstaCadastrado_AlunoCadastrado() {

        Aluno aluno = new Aluno();
        aluno.setId(1L);

        Curso curso = new Curso();
        curso.setIdDoCurso(1L);
        curso.getAlunos().add(aluno);

        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));

        boolean alunoCadastrado = alunoService.verificarSeAlunoEstaCadastrado(1L, 1L);

        assertTrue(alunoCadastrado);
    }

    @Test
    @DisplayName("Testar verificar aluno no curso - Aluno não cadastrado")
    public void testVerificarSeAlunoEstaCadastrado_AlunoNaoCadastrado() {

        Aluno aluno = new Aluno();
        aluno.setId(2L); 

        Curso curso = new Curso();
        curso.setIdDoCurso(1L);
        curso.getAlunos().add(aluno);

        when(alunoRepository.findById(1L)).thenReturn(Optional.of(new Aluno())); 
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));

        boolean alunoCadastrado = alunoService.verificarSeAlunoEstaCadastrado(1L, 1L);

        assertFalse(alunoCadastrado);
    }
}
