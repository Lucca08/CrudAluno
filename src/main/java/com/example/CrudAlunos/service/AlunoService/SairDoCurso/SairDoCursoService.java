package com.example.CrudAlunos.service.AlunoService.SairDoCurso;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CrudAlunos.dto.AlunoDTO;
import com.example.CrudAlunos.dto.CursoDTO;
import com.example.CrudAlunos.model.Aluno;
import com.example.CrudAlunos.model.Curso;
import com.example.CrudAlunos.repository.AlunoRepository;
import com.example.CrudAlunos.repository.CursoRepository;
import com.example.CrudAlunos.service.AlunoService.CadastrarAluno.CadastrarAlunoService;

import jakarta.transaction.Transactional;

@Service
public class SairDoCursoService {

    private static final Logger logger = Logger.getLogger(CadastrarAlunoService.class.getName());

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

  
    @Transactional  
    public void sairDoCurso(AlunoDTO alunoDTO, Long idCurso) {
        Aluno aluno = alunoRepository.findById(alunoDTO.getId()).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        Curso curso = cursoRepository.findById(idCurso).orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        Logger.getLogger("Removendo aluno do curso: " + aluno.getNome() + " do curso: " + curso.getNome());

        curso.getAlunos().remove(aluno);
        cursoRepository.atualizarCurso(curso);
    }
}
