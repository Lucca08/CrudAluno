package com.example.CrudAlunos.dto;

import com.example.CrudAlunos.model.Professor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfessorDTO {
    private Long idDoProfessor;
    private String nome;

    public ProfessorDTO(Professor professor) {
        this.idDoProfessor = professor.getIdDoProfessor();
        this.nome = professor.getNome();
    }
}
