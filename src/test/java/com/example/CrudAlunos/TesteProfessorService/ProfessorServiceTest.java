package com.example.CrudAlunos.TesteProfessorService;

import com.example.CrudAlunos.dto.ProfessorDTO;
import com.example.CrudAlunos.model.Professor;
import com.example.CrudAlunos.repository.ProfessorRepository;
import com.example.CrudAlunos.service.ProfessorService.ProfessorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProfessorServiceTest {

    @Mock
    private ProfessorRepository professorRepository;

    @InjectMocks
    private ProfessorService professorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Testar criar professor")
    void testCriarProfessor() {
        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setNome("João da Silva");

        Professor professor = new Professor();
        professor.setIdDoProfessor(1L);
        professor.setNome(professorDTO.getNome());

        when(professorRepository.save(any(Professor.class))).thenReturn(professor);

        Professor result = professorService.criarProfessor(professorDTO);

        assertEquals(professor.getIdDoProfessor(), result.getIdDoProfessor());
        assertEquals(professor.getNome(), result.getNome());

        verify(professorRepository, times(1)).save(any(Professor.class));
    }
}
