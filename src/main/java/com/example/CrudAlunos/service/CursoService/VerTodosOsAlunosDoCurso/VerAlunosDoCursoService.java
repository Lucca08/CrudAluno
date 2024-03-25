package com.example.CrudAlunos.service.CursoService.VerTodosOsAlunosDoCurso;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CrudAlunos.dto.AlunoDTO;
import com.example.CrudAlunos.model.Curso;
import com.example.CrudAlunos.repository.CursoRepository;

@Service
public class VerAlunosDoCursoService {

    private static final Logger logger = Logger.getLogger(VerAlunosDoCursoService.class.getName());

    @Autowired
    private CursoRepository cursoRepository;

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
}
