package com.example.CrudAlunos.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.CrudAlunos.model.Aluno;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String matricula;
    private CursoDTO curso;

    public AlunoDTO(Aluno aluno) {
        this.id = aluno.getId();
        this.nome = aluno.getNome();
        this.cpf = aluno.getCpf();
        this.matricula = aluno.getMatricula();    
    }

}
