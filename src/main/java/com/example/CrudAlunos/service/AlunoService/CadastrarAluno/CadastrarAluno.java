package com.example.CrudAlunos.service.AlunoService.CadastrarAluno;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CrudAlunos.dto.AlunoDTO;
import com.example.CrudAlunos.model.Aluno;
import com.example.CrudAlunos.model.Curso;
import com.example.CrudAlunos.repository.AlunoRepository;
import com.example.CrudAlunos.service.CursoService.CursoService;

import jakarta.transaction.Transactional;

@Service
public class CadastrarAluno {

    private static final Logger logger = Logger.getLogger(CadastrarAluno.class.getName());

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoService cursoService;

    @Transactional
    public Aluno cadastrarAlunoNoCurso(AlunoDTO alunoDTO) {
        Aluno aluno = alunoRepository.findById(alunoDTO.getId()).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        Curso curso = cursoService.encontrarCursoPorId(alunoDTO.getIdCurso());  

        logger.info("Cadastrando aluno no curso: " + aluno.getNome() + " no curso: " + curso.getNome());

        curso.getAlunos().add(aluno);
        cursoService.atualizarCurso(curso);
    
        return aluno;
    }
    
  
}
