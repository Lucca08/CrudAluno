package com.example.CrudAlunos.TesteAlunoService.TestAtualizarAluno; 

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.CrudAlunos.dto.AlunoDTO;
import com.example.CrudAlunos.model.Aluno;
import com.example.CrudAlunos.repository.AlunoRepository;
import com.example.CrudAlunos.service.AlunoService.AtualizarAluno.AtualizarAlunoService;
import com.example.CrudAlunos.Stub.StubAlunos; 

@ExtendWith(MockitoExtension.class)
public class AtualizarAlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private AtualizarAlunoService atualizarAlunoService;

    @DisplayName("Teste de atualização de aluno")
    @Test
    public void testAtualizarAluno() {
        Aluno alunoExistente = StubAlunos.AlunoStub1(); 

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(1L);
        alunoDTO.setNome("Novo Nome");

        when(alunoRepository.findById(1L)).thenReturn(Optional.of(alunoExistente));
        when(alunoRepository.save(any(Aluno.class))).thenAnswer(invocation -> {
            Aluno alunoSalvo = invocation.getArgument(0);
            alunoExistente.setNome(alunoSalvo.getNome());
            return alunoExistente;
        });

        Aluno alunoAtualizado = atualizarAlunoService.atualizarAluno(alunoDTO);
        assertEquals(alunoDTO.getNome(), alunoAtualizado.getNome());
    }
}
