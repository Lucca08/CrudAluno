package com.example.CrudAlunos.service.AlunoService.BuscarSeAlunoEstaCadastrado;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CrudAlunos.dto.AlunoDTO;
import com.example.CrudAlunos.model.Aluno;
import com.example.CrudAlunos.model.Curso;
import com.example.CrudAlunos.repository.AlunoRepository;

@Service
public class BuscarCadastroService {

    @Autowired
    private AlunoRepository alunoRepository;

    public boolean verificarAlunoNoCurso(AlunoDTO alunoDTO, Long idCurso) {
        Optional<Aluno> optionalAluno = alunoRepository.findById(alunoDTO.getId());
        
        if (optionalAluno.isPresent()) {
            Aluno aluno = optionalAluno.get();
            for (Curso curso : aluno.getCursos()) {
                if (curso.getId() == idCurso) {
                    return true; 
                }
                
            }
        }

        return false;
    }
}
