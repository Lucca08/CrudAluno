package com.example.CrudAlunos.service.AlunoService.CadastrarAluno;
import com.example.CrudAlunos.dto.AlunoDTO;
import com.example.CrudAlunos.model.Aluno;
import com.example.CrudAlunos.model.Curso;
import com.example.CrudAlunos.repository.AlunoRepository;
import com.example.CrudAlunos.repository.CursoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@Service
public class CadastrarAlunoService {
    public static final Logger logger = Logger.getLogger(CadastrarAlunoService.class.getName());
    
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    // Método setter para o repositório de aluno
    public void setAlunoRepository(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    // Método setter para o repositório de curso
    public void setCursoRepository(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Transactional
    public Aluno cadastrarAluno(AlunoDTO alunoDTO) {
        Aluno aluno = new Aluno();
        aluno.setNome(alunoDTO.getNome());
        aluno.setCpf(alunoDTO.getCpf());
        aluno.setMatricula(alunoDTO.getMatricula());
        
        Aluno alunoSalvo = alunoRepository.save(aluno);

        if (alunoDTO.getCurso() != null) {
            Curso curso = cursoRepository.findById(alunoDTO.getCurso().getIdDoCurso())
                                          .orElseThrow(() -> new RuntimeException("Curso não encontrado com o ID: " + alunoDTO.getCurso().getIdDoCurso()));
            
            curso.getAlunos().add(alunoSalvo);
            cursoRepository.save(curso);
        }

        return alunoSalvo;
    }
}
