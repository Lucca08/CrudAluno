package com.example.CrudAlunos.service.CursoService;

import com.example.CrudAlunos.dto.AlunoDTO;
import com.example.CrudAlunos.dto.CursoDTO;
import com.example.CrudAlunos.model.Aluno;
import com.example.CrudAlunos.model.Curso;
import com.example.CrudAlunos.model.Professor;
import com.example.CrudAlunos.repository.AlunoRepository;
import com.example.CrudAlunos.repository.CursoRepository;
import com.example.CrudAlunos.repository.ProfessorRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class CursoService {
    private static final Logger logger = Logger.getLogger(CursoService.class.getName());
    
    private final CursoRepository cursoRepository;
    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;

    public CursoService(CursoRepository cursoRepository, AlunoRepository alunoRepository, ProfessorRepository professorRepository) {
        this.cursoRepository = cursoRepository;
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
    }
  

    @Transactional(readOnly = true)
    public Curso cadastrarCurso(CursoDTO cursoDTO) {
        Curso curso = new Curso();
        curso.setNome(cursoDTO.getNome());
        return cursoRepository.save(curso);
    }

    @Transactional(readOnly = true)
    public Curso buscarCursoPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado com o ID: " + id));
    }


    @Transactional
    public void deletarCurso(Long id) {
        logger.info("Deletando curso com ID: " + id);
        cursoRepository.deleteById(id);
    }

    @Transactional
    public Curso criarCurso(CursoDTO cursoDTO) {
        if (cursoDTO.getDescricao() == null || cursoDTO.getDescricao().isEmpty()) {
            throw new IllegalArgumentException("Descrição do curso é obrigatória.");
        }

        if (cursoDTO.getProfessor() != null) {
            if (cursoDTO.getProfessor().getIdDoProfessor() != null) {
                Professor professor = professorRepository.findById(cursoDTO.getProfessor().getIdDoProfessor())
                        .orElseThrow(() -> new RuntimeException(
                                "Professor não encontrado com ID: " + cursoDTO.getProfessor().getIdDoProfessor()));
                return criarCursoComProfessor(cursoDTO, professor);
            } else {
                Professor novoProfessor = new Professor();
                novoProfessor.setNome(cursoDTO.getProfessor().getNome());
                Professor professorSalvo = professorRepository.save(novoProfessor);
                return criarCursoComProfessor(cursoDTO, professorSalvo);
            }
        } else {
            throw new RuntimeException("Professor não fornecido");
        }
    }

    @Transactional
    public Curso criarCursoComProfessor(CursoDTO cursoDTO, Professor professor) {
        Curso curso = new Curso();
        curso.setNome(cursoDTO.getNome());
        curso.setProfessor(professor);
        curso.setDescricao(cursoDTO.getDescricao());
        

        cursoRepository.save(curso);

        logger.info("Curso criado com sucesso: " + curso.getNome());

        return curso;
    }

    @Transactional
    public Curso atualizarCurso(CursoDTO cursoDTO) {
        Curso curso = cursoRepository.findById(cursoDTO.getIdDoCurso())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        curso.setNome(cursoDTO.getNome());

        logger.info("Atualizando curso: " + curso.getNome());

        return cursoRepository.save(curso);
    }

    @Transactional
    public void excluirCurso(Long idCurso) {
        Optional<Curso> optionalCurso = cursoRepository.findById(idCurso);
        
        if (optionalCurso.isPresent()) {
            Curso curso = optionalCurso.get();
            cursoRepository.delete(curso);
            logger.info("Curso excluído com sucesso: " + curso);
        } else {
            logger.warning("Não foi possível encontrar o curso com o ID: " + idCurso);
            throw new RuntimeException("Não foi possível encontrar o curso com o ID: " + idCurso);
        }
    }

   @Transactional
    public boolean verificarAlunoNoCurso(Long idAluno, Long idCurso) {
        Aluno aluno = alunoRepository.findById(idAluno)
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

    public List<AlunoDTO> verAlunosDoCurso(Long idCurso) {
        Curso curso = cursoRepository.findById(idCurso).orElseThrow(() -> {
            logger.warning("Não foi possível encontrar o curso com o ID: " + idCurso);
            throw new RuntimeException("Não foi possível encontrar o curso com o ID: " + idCurso);
        });

        return curso.getAlunos().stream().map(aluno -> {
            AlunoDTO alunoDTO = new AlunoDTO();
            alunoDTO.setNome(aluno.getNome());
            alunoDTO.setCpf(aluno.getCpf());
            alunoDTO.setMatricula(aluno.getMatricula());
            return alunoDTO;
        }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CursoDTO> listarTodosCursos() {
        List<Curso> cursos = cursoRepository.findAll();
        logger.info("Listando todos os cursos: " + cursos.size() + " encontrados");
    
        return cursos.stream()
                     .map(curso -> {
                         CursoDTO cursoDTO = new CursoDTO();
                         cursoDTO.setIdDoCurso(curso.getIdDoCurso());
                         cursoDTO.setNome(curso.getNome());
                         cursoDTO.setDescricao(curso.getDescricao());
                         return cursoDTO;
                     })
                     .collect(Collectors.toList());
    }
    

    
}
