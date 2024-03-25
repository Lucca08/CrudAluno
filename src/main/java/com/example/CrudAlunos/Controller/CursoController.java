package com.example.CrudAlunos.Controller;

import com.example.CrudAlunos.dto.AlunoDTO;
import com.example.CrudAlunos.dto.CursoDTO;
import com.example.CrudAlunos.dto.ProfessorDTO;
import com.example.CrudAlunos.model.Curso;
import com.example.CrudAlunos.model.Professor;
import com.example.CrudAlunos.service.CursoService.AtualizarCurso.AtualizarCursoService;
import com.example.CrudAlunos.service.CursoService.CriarCurso.CriaCursoService;
import com.example.CrudAlunos.service.CursoService.ExcluirCurso.ExcluirCursoService;
import com.example.CrudAlunos.service.CursoService.VerTodosOsAlunosDoCurso.VerAlunosDoCursoService;
import com.example.CrudAlunos.service.CursoService.VerTodosOsCursos.VerTodosOsCursos;
import com.example.CrudAlunos.service.ProfessorService.CriarProfessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final VerTodosOsCursos verTodosOsCursosService;
    private final VerAlunosDoCursoService verAlunosDoCursoService;
    private final CriaCursoService criarCursoService;
    private final ExcluirCursoService excluirCursoService;
    private final AtualizarCursoService atualizarCursoService;
    private final CriarProfessor professorService;
    

    @Autowired
    public CursoController(VerTodosOsCursos verTodosOsCursosService,
                           VerAlunosDoCursoService verAlunosDoCursoService,
                           CriaCursoService criarCursoService,
                           ExcluirCursoService excluirCursoService,
                           AtualizarCursoService atualizarCursoService,
                           CriarProfessor professorService) {
        this.verTodosOsCursosService = verTodosOsCursosService;
        this.verAlunosDoCursoService = verAlunosDoCursoService;
        this.criarCursoService = criarCursoService;
        this.excluirCursoService = excluirCursoService;
        this.atualizarCursoService = atualizarCursoService;
        this.professorService = professorService;
    }

    @GetMapping
    public ResponseEntity<List<CursoDTO>> listarTodosCursos() {
        List<CursoDTO> cursos = verTodosOsCursosService.listarTodosCursos();
        return new ResponseEntity<>(cursos, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CursoDTO> atualizarCurso(@RequestBody CursoDTO cursoDTO) {
        Curso cursoAtualizado = atualizarCursoService.atualizarCurso(cursoDTO);
        return new ResponseEntity<>(mapToCursoDTO(cursoAtualizado), HttpStatus.OK);
    }

    @GetMapping("/{idCurso}/alunos")
    public ResponseEntity<List<AlunoDTO>> verTodosAlunosDoCursoPorId(@PathVariable Long idCurso) {
        List<AlunoDTO> alunosDoCurso = verAlunosDoCursoService.verAlunosDoCurso(idCurso);
        return new ResponseEntity<>(alunosDoCurso, HttpStatus.OK);
    }
   
    @PostMapping("/cursos/criarcursos")
    public ResponseEntity<CursoDTO> criarCurso(@RequestBody CursoDTO cursoDTO) {
    Curso curso = criarCursoService.criarCurso(cursoDTO);
    CursoDTO cursoCriadoDTO = mapToCursoDTO(curso);
    return new ResponseEntity<>(cursoCriadoDTO, HttpStatus.CREATED);
    }

    
    
    private CursoDTO mapToCursoDTO(Curso curso) {
        CursoDTO cursoDTO = new CursoDTO();
        cursoDTO.setId(curso.getId());
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
        excluirCursoService.excluirCurso(idCurso);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
