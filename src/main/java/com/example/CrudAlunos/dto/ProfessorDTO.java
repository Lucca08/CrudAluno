package com.example.CrudAlunos.dto;

import com.example.CrudAlunos.model.Professor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfessorDTO {
    private Long id;
    private String nome;

  

    public ProfessorDTO(Professor professor) {
        this.id = professor.getId();
        this.nome = professor.getNome();
    }
}
