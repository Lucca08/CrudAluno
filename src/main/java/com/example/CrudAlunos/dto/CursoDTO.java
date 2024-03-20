package com.example.CrudAlunos.dto;

import com.example.CrudAlunos.model.Curso;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CursoDTO {
    private Long id;
    private String nome;
    private ProfessorDTO professor;
    private String descricao;


    public CursoDTO(Curso curso) {
        this.id = curso.getId();
        this.nome = curso.getNome();
        this.professor = new ProfessorDTO(curso.getProfessor());
        this.descricao = curso.getDescricao();
    }
}
