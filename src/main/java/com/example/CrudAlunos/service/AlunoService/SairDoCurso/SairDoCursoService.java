package com.example.CrudAlunos.service.AlunoService.SairDoCurso;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.example.CrudAlunos.dto.AlunoDTO;
import com.example.CrudAlunos.dto.CursoDTO;
import com.example.CrudAlunos.model.Aluno;
import com.example.CrudAlunos.model.Curso;
import com.example.CrudAlunos.repository.AlunoRepository;
import com.example.CrudAlunos.repository.CursoRepository;

@Service
public class SairDoCursoService {

    private static final Logger logger = Logger.getLogger(SairDoCursoService.class.getName());

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    public void sairDoCurso(AlunoDTO alunoDTO) {
        Aluno aluno = alunoRepository.findById(alunoDTO.getId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        Curso curso = cursoRepository.findById(alunoDTO.getCurso().getIdDoCurso())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado com o ID: " + alunoDTO.getCurso().getIdDoCurso()));

        curso.getAlunos().remove(aluno);
        cursoRepository.save(curso);

        logger.info("Aluno " + aluno.getNome() + " saiu do curso " + curso.getNome());
    }
}
