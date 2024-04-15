package com.example.CrudAlunos.Controller;

import com.example.CrudAlunos.dto.AlunoDTO;
import com.example.CrudAlunos.exception.AlunoJaMatriculadoException;
import com.example.CrudAlunos.exception.AlunoNaoEncontradoException;
import com.example.CrudAlunos.exception.CursoNaoEncontradoException;
import com.example.CrudAlunos.service.AlunoService.AlunoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    @Autowired
    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping()
    public ResponseEntity<Void> cadastrarAluno(@RequestBody AlunoDTO alunoDTO) {
        try {
            alunoService.cadastrarAluno(alunoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (AlunoJaMatriculadoException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{alunoId}")
    public ResponseEntity<Void> excluirAluno(@PathVariable long alunoId) {
    try {
        alunoService.deletarAluno(alunoId);
        return ResponseEntity.ok().build();
    } catch (AlunoNaoEncontradoException e) {
        return ResponseEntity.notFound().build();
    }
}


    @PutMapping("/{alunoId}")
    public ResponseEntity<Void> atualizarAluno(@PathVariable long alunoId, @RequestBody AlunoDTO alunoDTO) {
        try {
            alunoDTO.setId(alunoId);
            alunoService.atualizarAluno(alunoDTO);
            return ResponseEntity.ok().build();
        } catch (AlunoNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{alunoId}/cursos/{cursoId}")
    public ResponseEntity<Void> sairDoCurso(@PathVariable long alunoId, @PathVariable long cursoId) {
    try {
        alunoService.sairDoCurso(alunoId, cursoId);
        return ResponseEntity.ok().build();
    } catch (AlunoNaoEncontradoException | CursoNaoEncontradoException e) {
        return ResponseEntity.badRequest().build();
    }
}


    

    @GetMapping("/{alunoId}/cursos/{cursoId}")
    public ResponseEntity<Boolean> verificarAlunoNoCurso(@PathVariable long alunoId, @PathVariable long cursoId) {
        boolean alunoNoCurso = alunoService.verificarAlunoNoCurso(alunoId, cursoId);
        return ResponseEntity.ok(alunoNoCurso);
    }


}
