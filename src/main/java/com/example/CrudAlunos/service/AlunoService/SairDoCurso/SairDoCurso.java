package com.example.CrudAlunos.service.AlunoService.CadastrarAluno;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CrudAlunos.dto.CadastroAlunoDTO;
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
    public void sairDoCurso(CadastroAlunoDTO cadastroAlunoDTO) {
        Aluno aluno = alunoRepository.findById(cadastroAlunoDTO.getIdAluno()).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        Curso curso = cursoService.encontrarCursoPorId(cadastroAlunoDTO.getIdCurso());

        Logger.getLogger("Removendo aluno do curso: " + aluno.getNome() + " do curso: " + curso.getNome());

        curso.getAlunos().remove(aluno);
        cursoService.atualizarCurso(curso);
    }
}
