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

    @Transactional
    public Aluno cadastrarAluno(AlunoDTO alunoDTO) {
        Aluno aluno = new Aluno();
        aluno.setNome(alunoDTO.getNome());
        aluno.setCpf(alunoDTO.getCpf());
        aluno.setMatricula(alunoDTO.getMatricula());
        
        // Salvar o aluno no banco de dados
        Aluno alunoSalvo = alunoRepository.save(aluno);

        // Verificar se o curso está presente no DTO do aluno
        if (alunoDTO.getCurso() != null) {
            // Buscar o curso pelo ID presente no DTO do aluno
            Curso curso = cursoRepository.findById(alunoDTO.getCurso().getIdDoCurso())
                                          .orElseThrow(() -> new RuntimeException("Curso não encontrado com o ID: " + alunoDTO.getCurso().getIdDoCurso()));
            
            // Adicionar o aluno ao curso e salvar o curso
            curso.getAlunos().add(alunoSalvo);
            cursoRepository.save(curso);
        }

        return alunoSalvo;
    }
}
