package com.example.CrudAlunos.TesteAlunoService.TestDeletarAluno;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.CrudAlunos.Stub.StubAlunos;
import com.example.CrudAlunos.model.Aluno;
import com.example.CrudAlunos.repository.AlunoRepository;
import com.example.CrudAlunos.service.AlunoService.ExcluirAluno.ExcluirAlunoService;

public class ExcluirAlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;
    
    @InjectMocks
    private ExcluirAlunoService excluirAlunoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExcluirAlunoComSucesso() {
        Aluno aluno = StubAlunos.AlunoStub1();
        when(alunoRepository.findById(aluno.getId())).thenReturn(Optional.of(aluno));
        
        excluirAlunoService.deletarAluno(aluno.getId());
    }

    @Test
    public void testExcluirAlunoComIdInvalido() {
        when(alunoRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            excluirAlunoService.deletarAluno(999L);
        });
    }

    @Test
    public void testExcluirAlunoComIdNulo() {
        assertThrows(RuntimeException.class, () -> {
            excluirAlunoService.deletarAluno(null);
        });
    }
}
