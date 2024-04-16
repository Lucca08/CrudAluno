package com.example.CrudAlunos.Controller;

import com.example.CrudAlunos.dto.AlunoDTO;
import com.example.CrudAlunos.dto.CursoDTO;
import com.example.CrudAlunos.dto.ProfessorDTO;
import com.example.CrudAlunos.exception.CursoJaExistenteException;
import com.example.CrudAlunos.exception.CursoNaoEncontradoException;
import com.example.CrudAlunos.model.Curso;
import com.example.CrudAlunos.model.Professor;
import com.example.CrudAlunos.service.CursoService.CursoService;
import com.example.CrudAlunos.service.ProfessorService.ProfessorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;
    private final ProfessorService professorService;

    @Autowired
    public CursoController(CursoService cursoService, ProfessorService professorService) {
        this.cursoService = cursoService;
        this.professorService = professorService;
    }

    
    @GetMapping
    public ResponseEntity<List<CursoDTO>> listarTodosCursos() {
        List<CursoDTO> cursos = cursoService.listarTodosCursos();
        return new ResponseEntity<>(cursos, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CursoDTO> atualizarCurso(@RequestBody CursoDTO cursoDTO) {
        Curso curso = cursoService.atualizarCurso(cursoDTO);
        CursoDTO cursoDTOAtualizado = mapToCursoDTO(curso);
        return new ResponseEntity<>(cursoDTOAtualizado, HttpStatus.OK);
    }

    @GetMapping("/{idCurso}/alunos")
    public ResponseEntity<List<AlunoDTO>> verTodosAlunosDoCursoPorId(@PathVariable Long idCurso) {
        List<AlunoDTO> alunos = cursoService.verAlunosDoCurso(idCurso);
        return new ResponseEntity<>(alunos, HttpStatus.OK);
    }
   
    @PostMapping("/cursos/criarcursos")
    public ResponseEntity<CursoDTO> criarCurso(@RequestBody CursoDTO cursoDTO) {
    try {
        Curso curso = cursoService.criarCurso(cursoDTO);
        CursoDTO cursoDTOCriado = mapToCursoDTO(curso);
        return new ResponseEntity<>(cursoDTOCriado, HttpStatus.CREATED);
    } catch (CursoJaExistenteException e) {
        return ResponseEntity.badRequest().build();
    }
}

    private CursoDTO mapToCursoDTO(Curso curso) {
        CursoDTO cursoDTO = new CursoDTO();
        cursoDTO.setIdDoCurso(curso.getIdDoCurso());
        cursoDTO.setNome(curso.getNome());
        return cursoDTO;
    }

    @PostMapping("/professores")
    public ResponseEntity<Professor> criarProfessor(@RequestBody ProfessorDTO professorDTO) {
        Professor professor = professorService.criarProfessor(professorDTO);
        return new ResponseEntity<>(professor, HttpStatus.CREATED);
    }
    


    @DeleteMapping("/{idCurso}")
    public ResponseEntity<Void> excluirCurso(@PathVariable Long idCurso) {
            try {
                cursoService.excluirCurso(idCurso);
                return ResponseEntity.ok().build();
            } catch (CursoNaoEncontradoException e) {
                return ResponseEntity.notFound().build();
        }
    }

   
    
}
