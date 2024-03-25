package com.example.CrudAlunos.TesteCursoService.TesteCriaCurso;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.CrudAlunos.dto.CursoDTO;
import com.example.CrudAlunos.dto.ProfessorDTO;
import com.example.CrudAlunos.model.Curso;
import com.example.CrudAlunos.model.Professor;
import com.example.CrudAlunos.repository.CursoRepository;
import com.example.CrudAlunos.repository.ProfessorRepository;
import com.example.CrudAlunos.service.CursoService.CriarCurso.CriaCursoService;

public class TesteCriaCurso {

    @Mock
    private CursoRepository cursoRepository;

    @Mock
    private ProfessorRepository professorRepository;

    @InjectMocks
    private CriaCursoService criaCursoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCriarCursoComProfessorExistente() {
        CursoDTO cursoDTO = new CursoDTO();
        cursoDTO.setNome("Curso de Teste");
        cursoDTO.setDescricao("Descrição do Curso de Teste"); // Adicionando descrição do curso
    
        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setIdDoProfessor(1L); 
        professorDTO.setNome("Professor Teste");
        cursoDTO.setProfessor(professorDTO);
    
        Long idProfessor = 1L;
    
        Professor professor = new Professor();
        professor.setIdDoProfessor(idProfessor);
        professor.setNome("Professor Teste");
    
        when(professorRepository.findById(idProfessor)).thenReturn(Optional.of(professor));
    
        Curso cursoSalvo = new Curso();
        cursoSalvo.setIdDoCurso(1L);
        cursoSalvo.setNome(cursoDTO.getNome());
        cursoSalvo.setProfessor(professor);
        cursoSalvo.setDescricao(cursoDTO.getDescricao()); // Utilizando a descrição do DTO
    
        when(cursoRepository.save(any(Curso.class))).thenReturn(cursoSalvo);
    
        Curso cursoCriado = criaCursoService.criarCurso(cursoDTO);
    
        assertNotNull(cursoCriado);
        assertEquals(cursoDTO.getNome(), cursoCriado.getNome());
        assertEquals(professor, cursoCriado.getProfessor());
        assertEquals(cursoDTO.getDescricao(), cursoCriado.getDescricao());
    }
    
}