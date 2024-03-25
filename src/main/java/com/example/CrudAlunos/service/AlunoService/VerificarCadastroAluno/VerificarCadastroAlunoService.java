package com.example.CrudAlunos.service.AlunoService.VerificarCadastroAluno;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CrudAlunos.dto.AlunoDTO;
import com.example.CrudAlunos.dto.CursoDTO;
import com.example.CrudAlunos.model.Aluno;
import com.example.CrudAlunos.model.Curso;
import com.example.CrudAlunos.repository.AlunoRepository;
import com.example.CrudAlunos.repository.CursoRepository;


@Service
public class VerificarCadastroAlunoService {

    private static final Logger logger = Logger.getLogger(VerificarCadastroAlunoService.class.getName());

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public boolean verificarSeAlunoEstaCadastrado(long id, long idCurso) {
        try {
            Aluno aluno = alunoRepository.findById(id)
                                         .orElseThrow(() -> new RuntimeException("Aluno não encontrado com o ID: " + id)) ;

            Curso curso = cursoRepository.findById(idCurso)
                                     .orElseThrow(() -> new RuntimeException("Curso não encontrado com o ID: " + idCurso));

            boolean alunoCadastrado = !curso.getAlunos().stream()
                                                .filter(a -> a.getId().equals(aluno.getId()))
                                                .toList().isEmpty();

            logger.info("Verificando se o aluno " + aluno.getNome() + " está cadastrado no curso " + curso.getNome() + ": " + alunoCadastrado);

            return alunoCadastrado;
        } catch (Exception e) {
            logger.severe("Erro ao verificar se o aluno está cadastrado no curso: " + e.getMessage());
            throw e;
        }
    }

}
