package com.example.CrudAlunos.service.AlunoService.AtualizarAluno;

import java.util.logging.Logger;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CrudAlunos.dto.AlunoDTO;
import com.example.CrudAlunos.model.Aluno;
import com.example.CrudAlunos.repository.AlunoRepository;

import jakarta.transaction.Transactional;

@Service
public class AtualizarAlunoService {

    private static final Logger logger = Logger.getLogger(AtualizarAlunoService.class.getName());

    @Autowired
    private AlunoRepository alunoRepository;

    @Transactional
    public Aluno atualizarAluno(AlunoDTO alunoDTO) {
        Aluno aluno = alunoRepository.findById(alunoDTO.getId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        BeanUtils.copyProperties(alunoDTO, aluno);

        logger.info("Atualizando aluno: " + aluno.getNome());

        return alunoRepository.save(aluno); 
    }
}
