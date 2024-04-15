package com.example.CrudAlunos.dto;


import com.example.CrudAlunos.model.Aluno;
import com.example.CrudAlunos.model.Curso;

import java.util.ArrayList;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AlunoDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String matricula;
    private List<CursoDTO> cursos;
    private CursoDTO curso;

    public AlunoDTO(Aluno aluno) {
        this.id = aluno.getId();
        this.nome = aluno.getNome();
        this.cpf = aluno.getCpf();
        this.matricula = aluno.getMatricula();  

        this.cursos = new ArrayList<>();

        for (Curso curso : aluno.getCursos()) {
            this.cursos.add(new CursoDTO(curso));
        }
    }
}
