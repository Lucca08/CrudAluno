package com.example.CrudAlunos.service.AlunoService.AtualizarAluno;
package com.example.CrudAlunos.service.AlunoService.AtualizarAluno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CrudAlunos.dto.AlunoDTO;
import com.example.CrudAlunos.model.Aluno;
import com.example.CrudAlunos.repository.AlunoRepository;

@Service
public class AtualizarAlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno atualizarAluno(AlunoDTO alunoDTO) {
        Aluno aluno = alunoRepository.findById(alunoDTO.getId())
                                      .orElseThrow(() -> new RuntimeException("Aluno nao encontrado"));
        aluno.setNome(alunoDTO.getNome());

        return alunoRepository.save(aluno);
    }
}
