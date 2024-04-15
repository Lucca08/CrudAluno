package com.example.CrudAlunos.TesteCursoService;

import com.example.CrudAlunos.dto.AlunoDTO;
import com.example.CrudAlunos.dto.CursoDTO;
import com.example.CrudAlunos.dto.ProfessorDTO;
import com.example.CrudAlunos.model.Aluno;
import com.example.CrudAlunos.model.Curso;
import com.example.CrudAlunos.model.Professor;
import com.example.CrudAlunos.repository.AlunoRepository;
import com.example.CrudAlunos.repository.CursoRepository;
import com.example.CrudAlunos.repository.ProfessorRepository;
import com.example.CrudAlunos.service.CursoService.CursoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CursoServiceTest {

    @InjectMocks
    private CursoService cursoService;

    @Mock
    private CursoRepository cursoRepository;

    @Mock
    private AlunoRepository alunoRepository;

    @Mock
    private ProfessorRepository professorRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Testar cadastrar curso")
    public void testCadastrarCurso() {
        CursoDTO cursoDTO = new CursoDTO();
        cursoDTO.setNome("Curso de Teste");

        Curso cursoSalvo = new Curso();
        cursoSalvo.setIdDoCurso(1L);
        cursoSalvo.setNome("Curso de Teste");

        when(cursoRepository.save(any(Curso.class))).thenReturn(cursoSalvo);

        Curso cursoRetornado = cursoService.cadastrarCurso(cursoDTO);

        assertNotNull(cursoRetornado);
        assertEquals(cursoSalvo.getNome(), cursoRetornado.getNome());
    }

    @Test
    @DisplayName("Testar buscar curso por id existente")
    public void testBuscarCursoPorId_Existente() {
        
        Curso curso = new Curso();
        curso.setIdDoCurso(1L);
        curso.setNome("Curso de Teste");

        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));

        Curso cursoRetornado = cursoService.buscarCursoPorId(1L);

        assertNotNull(cursoRetornado);
        assertEquals(curso.getNome(), cursoRetornado.getNome());
    }

    @Test
    @DisplayName("Testar buscar curso por id não existente")
    public void testBuscarCursoPorId_NaoExistente() {
        when(cursoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> cursoService.buscarCursoPorId(1L));
    }

    
    @Test
    @DisplayName("Testar deletar curso")
    public void testDeletarCurso() {
        Curso curso = new Curso();
        curso.setIdDoCurso(1L);
        curso.setNome("Curso de Teste");

        doNothing().when(cursoRepository).deleteById(1L);

        cursoService.deletarCurso(1L);

        verify(cursoRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Testar criar curso")
    public void testCriarCurso() {
        CursoDTO cursoDTO = new CursoDTO();
        cursoDTO.setNome("Curso de Teste");
        cursoDTO.setDescricao("Curso de Teste");

        Professor professor = new Professor();
        professor.setIdDoProfessor(1L);
        professor.setNome("Professor Teste");

        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setIdDoProfessor(professor.getIdDoProfessor());
        professorDTO.setNome(professor.getNome());

        cursoDTO.setProfessor(professorDTO);

        Curso cursoSalvo = new Curso();
        cursoSalvo.setIdDoCurso(1L);
        cursoSalvo.setNome("Curso de Teste");
        cursoSalvo.setDescricao("Curso de Teste");
        cursoSalvo.setProfessor(professor);

        when(professorRepository.findById(1L)).thenReturn(Optional.of(professor));
        when(cursoRepository.save(any(Curso.class))).thenReturn(cursoSalvo);

        Curso cursoRetornado = cursoService.criarCurso(cursoDTO);

        assertNotNull(cursoRetornado);
        assertEquals(cursoSalvo.getNome(), cursoRetornado.getNome());
        assertEquals(cursoSalvo.getDescricao(), cursoRetornado.getDescricao());
        assertEquals(cursoSalvo.getProfessor().getNome(), cursoRetornado.getProfessor().getNome());
    }

    @Test
    @DisplayName("Testar criar curso sem professor")
    public void testCriarCurso_SemProfessor() {
        
        CursoDTO cursoDTO = new CursoDTO();
        cursoDTO.setNome("Curso de Teste");
        cursoDTO.setDescricao("Curso de Teste");

        when(professorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> cursoService.criarCurso(cursoDTO));
    }

    @Test
    @DisplayName("Testar criar curso com professor não encontrado")
    public void testCriarCurso_ProfessorNaoEncontrado() {
        CursoDTO cursoDTO = new CursoDTO();
        cursoDTO.setNome("Curso de Teste");
        cursoDTO.setDescricao("Curso de Teste");

        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setIdDoProfessor(1L);
        professorDTO.setNome("Professor Teste");

        cursoDTO.setProfessor(professorDTO);

        when(professorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> cursoService.criarCurso(cursoDTO));
    }

    @Test
    @DisplayName("excluir curso inexistente")
    public void testExcluirCursoInexistente() {
        when(cursoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> cursoService.excluirCurso(1L));
    }

    @Test
    @DisplayName("Testar atualizar curso")
    public void testAtualizarCurso() {
        Curso cursoExistente = new Curso();
        cursoExistente.setIdDoCurso(1L);
        cursoExistente.setNome("Curso Existente");

        CursoDTO cursoDTO = new CursoDTO();
        cursoDTO.setIdDoCurso(1L);
        cursoDTO.setNome("Novo Nome do Curso");

        when(cursoRepository.findById(1L)).thenReturn(Optional.of(cursoExistente));
        when(cursoRepository.save(any(Curso.class))).thenReturn(cursoExistente);

        Curso cursoAtualizado = cursoService.atualizarCurso(cursoDTO);

        assertEquals(cursoDTO.getNome(), cursoAtualizado.getNome());
    }

    @Test
    @DisplayName("verificar aluno no curso")
    public void testVerificarAlunoNoCurso() {
        
        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNome("Aluno Teste");

        Curso curso = new Curso();
        curso.setIdDoCurso(1L);
        curso.setNome("Curso Teste");
        curso.getAlunos().add(aluno);

        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));

        assertTrue(cursoService.verificarAlunoNoCurso(1L, 1L));
    }

    @Test
    @DisplayName("ver aluno do curso")
    public void testVerAlunosDoCurso() {
        Curso curso = new Curso();
        curso.setIdDoCurso(1L);
        curso.setNome("Curso Teste");

        Aluno aluno1 = new Aluno();
        aluno1.setId(1L);
        aluno1.setNome("Aluno 1");

        Aluno aluno2 = new Aluno();
        aluno2.setId(2L);
        aluno2.setNome("Aluno 2");

        curso.getAlunos().add(aluno1);
        curso.getAlunos().add(aluno2);

        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));

        List<AlunoDTO> alunosDoCurso = cursoService.verAlunosDoCurso(1L);

        assertEquals(2, alunosDoCurso.size());
        assertEquals("Aluno 1", alunosDoCurso.get(0).getNome());
        assertEquals("Aluno 2", alunosDoCurso.get(1).getNome());
    }

    @Test
    @DisplayName("Testar criar curso sem professor")
    public void testCriarCursoSemProfessor() {
        
        CursoDTO cursoDTO = new CursoDTO();
        cursoDTO.setNome("Curso de Teste");
        cursoDTO.setDescricao("Curso de Teste");

        assertThrows(RuntimeException.class, () -> cursoService.criarCurso(cursoDTO));
    }

    @Test
    @DisplayName("Listar todos os cursos")
    public void testListarTodosCursos() {

        Curso curso1 = new Curso();
        curso1.setIdDoCurso(1L);
        curso1.setNome("Curso 1");

        Curso curso2 = new Curso();
        curso2.setIdDoCurso(2L);
        curso2.setNome("Curso 2");

        List<Curso> cursos = new ArrayList<>();
        cursos.add(curso1);
        cursos.add(curso2);

        when(cursoRepository.findAll()).thenReturn(cursos);

        List<CursoDTO> cursosRetornados = cursoService.listarTodosCursos();

        assertEquals(2, cursosRetornados.size());
        assertEquals("Curso 1", cursosRetornados.get(0).getNome());
        assertEquals("Curso 2", cursosRetornados.get(1).getNome());
    }
}