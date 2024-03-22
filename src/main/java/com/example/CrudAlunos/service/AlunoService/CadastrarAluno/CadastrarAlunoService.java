package com.example.CrudAlunos.service.AlunoService.CadastrarAluno;

import com.example.CrudAlunos.dto.AlunoDTO;
import com.example.CrudAlunos.model.Aluno;
import com.example.CrudAlunos.model.Curso;
import com.example.CrudAlunos.repository.AlunoRepository;
import com.example.CrudAlunos.repository.CursoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CadastrarAlunoService {

    private static final Logger logger = Logger.getLogger(CadastrarAlunoService.class.getName());

    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;

    @Autowired
    public CadastrarAlunoService(AlunoRepository alunoRepository, CursoRepository cursoRepository) {
        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
    }

    @Transactional
    public Aluno cadastrarAlunoNoCurso(AlunoDTO alunoDTO, Long idCurso) throws Exception {
        Optional<Curso> cursoOptional = cursoRepository.findById(idCurso);
        if (cursoOptional.isEmpty()) {
            throw new Exception("Curso não encontrado");
        }

        Curso curso = cursoOptional.get();

        Aluno aluno = new Aluno();
        aluno.setNome(alunoDTO.getNome());

        if (curso.getAlunos() == null) {
            curso.setAlunos(new ArrayList<>());
        }

        curso.getAlunos().add(aluno);
        aluno.getCursos().add(curso);

        Aluno alunoCadastrado = alunoRepository.save(aluno);

        logger.info("Aluno cadastrado com sucesso no curso: " + curso.getNome());

        return alunoCadastrado;
    }
}
