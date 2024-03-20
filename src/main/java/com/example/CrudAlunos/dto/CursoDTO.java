package com.example.CrudAlunos.dto;

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
}
