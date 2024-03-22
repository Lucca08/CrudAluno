package com.example.CrudAlunos.service.AlunoService.CadastrarAluno;

import com.example.CrudAlunos.dto.AlunoDTO;
import com.example.CrudAlunos.model.Aluno;
import com.example.CrudAlunos.model.Curso;
import com.example.CrudAlunos.repository.AlunoRepository;
import com.example.CrudAlunos.repository.CursoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataLocationNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CadastrarAlunoService {

    private static final Logger logger = Logger.getLogger(CadastrarAlunoService.class.getName());

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    public Aluno cadastrarAlunoNoCurso(AlunoDTO alunoDTO, Long idCurso) throws Exception{
        Optional<Curso> cursoOptional = cursoRepository.findById(idCurso);
        if (cursoOptional.isEmpty()) {
            throw new Exception("Curso não encontrado");
        }

        Curso curso = cursoOptional.get();

        Aluno aluno = new Aluno();
        aluno.setNome(alunoDTO.getNome());

        curso.getAlunos().add(aluno); 
        aluno.getCursos().add(curso); 

        Aluno alunoCadastrado = alunoRepository.save(aluno);

        logger.info("Aluno cadastrado com sucesso no curso: " + curso.getNome());

        return alunoCadastrado;
    }
}
