package com.example.CrudAlunos.service.AlunoService.ExcluirAluno;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CrudAlunos.model.Aluno;
import com.example.CrudAlunos.repository.AlunoRepository;

import jakarta.transaction.Transactional;

@Service
public class ExcluirAlunoService {

    private static final Logger logger = Logger.getLogger(ExcluirAlunoService.class.getName());

    public static final String Aluno_NAO_ENCONTRADA = "Aluno não encontrado";

    @Autowired
    private AlunoRepository alunoRepository;

    @Transactional
    public Aluno deletarAluno(Long alunoId) {
        logger.info("Deletando aluno com ID: " + alunoId);
        return alunoRepository.findById(alunoId)
                .map(pessoa -> {
                    alunoRepository.delete(pessoa);
                    return pessoa;
                })
                .orElseThrow(() -> new RuntimeException(Aluno_NAO_ENCONTRADA + " ID: " + alunoId));
    }
}
