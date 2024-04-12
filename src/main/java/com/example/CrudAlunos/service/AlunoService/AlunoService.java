package com.example.CrudAlunos.service.AlunoService;

import com.example.CrudAlunos.dto.AlunoDTO;
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
    public static final Logger logger = Logger.getLogger(AlunoService.class.getName());
    public static final String Aluno_NAO_ENCONTRADA = "Aluno não encontrado";

    
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
            
            if (curso.getAlunos().contains(alunoSalvo)) {
                throw new RuntimeException("Aluno já matriculado no curso");
            }
        
            curso.getAlunos().add(alunoSalvo);
            cursoRepository.save(curso);
        }

        return alunoSalvo;
    }

    @Transactional
    public Aluno atualizarAluno(AlunoDTO alunoDTO) {
        Aluno aluno = alunoRepository.findById(alunoDTO.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));

        BeanUtils.copyProperties(alunoDTO, aluno);

        logger.info("Atualizando aluno: " + aluno.getNome());

        try {
            return alunoRepository.save(aluno);
        } catch (Exception e) {
            logger.severe("Erro ao salvar aluno: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao salvar aluno");
        }
    }

    public boolean verificarAlunoNoCurso(AlunoDTO alunoDTO, Long idCurso) {
        Aluno aluno = alunoRepository.findById(alunoDTO.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));

        Curso curso = cursoRepository.findById(idCurso)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));

        if (curso.getAlunos().contains(aluno)) {
            logger.info("Aluno " + aluno.getNome() + " está cadastrado no curso " + curso.getNome());
            return true;
        }

        logger.info("Aluno " + aluno.getNome() + " não está cadastrado no curso " + curso.getNome());

        return false;
    }

    @Transactional
    public Aluno deletarAluno(Long alunoId) {
        logger.info("Deletando aluno com ID: " + alunoId);
        return alunoRepository.findById(alunoId)
                .map(pessoa -> {
                    alunoRepository.delete(pessoa);
                    return pessoa;
                })
                .orElseThrow(() -> new RuntimeException(Aluno_NAO_ENCONTRADA + " ID: " + alunoId));
    }

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

