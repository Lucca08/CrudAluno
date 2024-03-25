package com.example.CrudAlunos.TesteCursoService.TesteVerTodosOsAlunosDoCurso;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.CrudAlunos.dto.AlunoDTO;
import com.example.CrudAlunos.model.Aluno;
import com.example.CrudAlunos.model.Curso;
import com.example.CrudAlunos.model.Professor;
import com.example.CrudAlunos.repository.AlunoRepository;
import com.example.CrudAlunos.repository.CursoRepository;
import com.example.CrudAlunos.repository.ProfessorRepository;
import com.example.CrudAlunos.service.CursoService.VerTodosOsAlunosDoCurso.VerAlunosDoCursoService;

public class TesteVerAlunosDoCurso {

    @Mock
    private CursoRepository cursoRepository;

    @Mock
    private AlunoRepository alunoRepository;

    @Mock
    private ProfessorRepository professorRepository;

    @InjectMocks
    private VerAlunosDoCursoService verAlunosDoCursoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testVerAlunosDoCurso() {
        Curso curso = new Curso();
        curso.setId(1L);
        curso.setNome("Curso de Teste");
        curso.setDescricao("Curso de Teste");

        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNome("Aluno de Teste");
        aluno.setCpf("12345678901");
        aluno.setMatricula("123456");
        aluno.getCursos().add(curso);

        Professor professor = new Professor();
        professor.setId(1L);
        professor.setNome("Professor Teste");

        curso.setProfessor(professor);

        curso.getAlunos().add(aluno);

        when(cursoRepository.findById(1L)).thenReturn(java.util.Optional.of(curso));

        verAlunosDoCursoService.verAlunosDoCurso(1L);

        verify(cursoRepository, times(1)).findById(1L);

        
    }
}
