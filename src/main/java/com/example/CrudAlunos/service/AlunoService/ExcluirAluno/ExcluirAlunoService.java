package com.example.CrudAlunos.service.AlunoService.ExcluirAluno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CrudAlunos.model.Aluno;
import com.example.CrudAlunos.repository.AlunoRepository;

import jakarta.transaction.Transactional;

@Service
public class ExcluirAlunoService {
    
    @Autowired
    private AlunoRepository alunoRepository;

    @Transactional
    public Aluno excluirAluno(Long idAluno) {
        Aluno aluno = alunoRepository.findById(idAluno)
                                      .orElseThrow(() -> new RuntimeException("Aluno nao encontrado"));
        alunoRepository.delete(aluno);
        return aluno;
    }
}
