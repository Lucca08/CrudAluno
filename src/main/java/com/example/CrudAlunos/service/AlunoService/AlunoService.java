package com.example.CrudAlunos.service.AlunoService;

import com.example.CrudAlunos.dto.AlunoDTO;
import com.example.CrudAlunos.exception.AlunoJaMatriculadoException;
import com.example.CrudAlunos.exception.AlunoNaoEncontradoException;
import com.example.CrudAlunos.model.Aluno;
import com.example.CrudAlunos.model.Curso;
import com.example.CrudAlunos.repository.AlunoRepository;
import com.example.CrudAlunos.repository.CursoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.logging.Logger;

@Service
public class AlunoService {
    private static final Logger logger = Logger.getLogger(AlunoService.class.getName());
    private static final String ALUNO_NAO_ENCONTRADO = "Aluno não encontrado";
    private static final String ALUNO_JA_MATRICULADO = "Aluno já matriculado no curso";

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;
    
    @Autowired
    public AlunoService(AlunoRepository alunoRepository, CursoRepository cursoRepository) {
        this.alunoRepository = alunoRepository;
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

            if (curso.getAlunos().contains(alunoSalvo)) {
                throw new AlunoJaMatriculadoException(ALUNO_JA_MATRICULADO);
            }

            curso.getAlunos().add(alunoSalvo);
            cursoRepository.save(curso);
        }

        return alunoSalvo;
    }

    @Transactional
    public Aluno atualizarAluno(AlunoDTO alunoDTO) {
        Aluno aluno = alunoRepository.findById(alunoDTO.getId())
                .orElseThrow(() -> new AlunoNaoEncontradoException(ALUNO_NAO_ENCONTRADO));

        BeanUtils.copyProperties(alunoDTO, aluno);

        logger.info("Atualizando aluno: " + aluno.getNome());

        try {
            return alunoRepository.save(aluno);
        } catch (Exception e) {
            logger.severe("Erro ao salvar aluno: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao salvar aluno");
        }
    }

    @Transactional
    public boolean verificarAlunoNoCurso(long alunoId, long idCurso) {
        try {
            Aluno aluno = alunoRepository.findById(alunoId)
                    .orElseThrow(() -> new AlunoNaoEncontradoException("Aluno não encontrado com o ID: " + alunoId));

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

    @Transactional
    public Aluno deletarAluno(Long alunoId) {
        logger.info("Deletando aluno com ID: " + alunoId);
        return alunoRepository.findById(alunoId)
                .map(pessoa -> {
                    alunoRepository.delete(pessoa);
                    return pessoa;
                })
                .orElseThrow(() -> new AlunoNaoEncontradoException(ALUNO_NAO_ENCONTRADO + " ID: " + alunoId));
    }

    @Transactional
    public void sairDoCurso(long alunoId, long cursoId) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new AlunoNaoEncontradoException(ALUNO_NAO_ENCONTRADO + " ID: " + alunoId));

        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado com o ID: " + cursoId));

        curso.getAlunos().remove(aluno);
        cursoRepository.save(curso);
    }


    @Transactional
    public boolean verificarSeAlunoEstaCadastrado(long id, long idCurso) {
        try {
            Aluno aluno = alunoRepository.findById(id)
                    .orElseThrow(() -> new AlunoNaoEncontradoException("Aluno não encontrado com o ID: " + id));

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
