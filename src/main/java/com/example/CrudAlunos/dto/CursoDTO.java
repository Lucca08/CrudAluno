package com.example.CrudAlunos.dto;

import com.example.CrudAlunos.model.Curso;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CursoDTO {
    private Long idDoCurso;
    private String nome;
    private ProfessorDTO professor;
    private String descricao;

    public CursoDTO(Long idDoCurso, String nome, ProfessorDTO professor, String descricao) {
        this.idDoCurso = idDoCurso;
        this.nome = nome;
        this.professor = professor;
        this.descricao = descricao;
    }

    public void setIdDoCurso(Long idDoCurso) {
        this.idDoCurso = idDoCurso;
    }

    public CursoDTO(Curso curso) {
        this.idDoCurso = curso.getIdDoCurso();
        this.nome = curso.getNome();
        this.professor = new ProfessorDTO(curso.getProfessor());
        this.descricao = curso.getDescricao();
    }

   
}
