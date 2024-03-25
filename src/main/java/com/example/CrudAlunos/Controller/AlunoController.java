package com.example.CrudAlunos.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CrudAlunos.dto.AlunoDTO;
import com.example.CrudAlunos.dto.CursoDTO;
import com.example.CrudAlunos.model.Aluno;
import com.example.CrudAlunos.model.Curso;
import com.example.CrudAlunos.service.AlunoService.AtualizarAluno.AtualizarAlunoService;
import com.example.CrudAlunos.service.AlunoService.CadastrarAluno.CadastrarAlunoService;
import com.example.CrudAlunos.service.AlunoService.ExcluirAluno.ExcluirAlunoService;
import com.example.CrudAlunos.service.AlunoService.SairDoCurso.SairDoCursoService;
import com.example.CrudAlunos.service.AlunoService.VerificarCadastroAluno.VerificarCadastroAlunoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final CadastrarAlunoService cadastrarAlunoService;
    private final ExcluirAlunoService excluirAlunoService;
    private final AtualizarAlunoService atualizarAlunoService;
    private final VerificarCadastroAlunoService verificarCadastroAluno;
    private final SairDoCursoService sairDoCursoService;

    @Autowired
    public AlunoController(CadastrarAlunoService cadastrarAlunoService, 
                           ExcluirAlunoService excluirAlunoService, 
                           AtualizarAlunoService atualizarAlunoService,
                           VerificarCadastroAlunoService verificarCadastroAluno,
                           SairDoCursoService sairDoCursoService) {
        this.cadastrarAlunoService = cadastrarAlunoService;
        this.excluirAlunoService = excluirAlunoService;
        this.atualizarAlunoService = atualizarAlunoService;
        this.verificarCadastroAluno = verificarCadastroAluno;
        this.sairDoCursoService = sairDoCursoService;
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> cadastrarAluno(@RequestBody AlunoDTO alunoDTO) {
        Aluno aluno = cadastrarAlunoService.cadastrarAluno(alunoDTO);
        return new ResponseEntity<>(mapToAlunoDTO(aluno), HttpStatus.CREATED);
    }

    @DeleteMapping("/{alunoId}")
    public ResponseEntity<Void> excluirAluno(@PathVariable long alunoId) {
        excluirAlunoService.deletarAluno(alunoId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{alunoId}")
    public ResponseEntity<AlunoDTO> atualizarAluno(@RequestBody AlunoDTO alunoDTO) {
        Aluno aluno = atualizarAlunoService.atualizarAluno(alunoDTO);
        return new ResponseEntity<>(mapToAlunoDTO(aluno), HttpStatus.OK);
    }


    @DeleteMapping("/{alunoId}/curso/{cursoId}")
    public ResponseEntity<Void> sairDoCurso(@RequestBody AlunoDTO alunoDTO,@PathVariable Long idCurso) {
        sairDoCursoService.sairDoCurso(alunoDTO, idCurso);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{alunoId}/curso/{cursoId}")
    public ResponseEntity<Boolean> verificarAlunoNoCurso(@RequestBody AlunoDTO alunoDTO,@PathVariable CursoDTO idCurso) {
        boolean alunoNoCurso = verificarCadastroAluno.verificarSeAlunoEstaCadastrado(alunoDTO, idCurso);
        return new ResponseEntity<>(alunoNoCurso, HttpStatus.OK);
    }

    private AlunoDTO mapToAlunoDTO(Aluno aluno) {
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(aluno.getId());
        alunoDTO.setNome(aluno.getNome());
        alunoDTO.setCpf(aluno.getCpf());
        alunoDTO.setMatricula(aluno.getMatricula());

        if (!aluno.getCursos().isEmpty()) {
            Curso curso = aluno.getCursos().get(0);
            alunoDTO.setCurso(new CursoDTO(curso));
        }

        return alunoDTO;
    }
}