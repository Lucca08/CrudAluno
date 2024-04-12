package com.example.CrudAlunos.dto;


import com.example.CrudAlunos.model.Aluno;
import com.example.CrudAlunos.model.Curso;

import java.util.ArrayList;
import java.util.List;

import org.checkerframework.checker.units.qual.C;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Alterar a propriedade curso para ser uma lista de CursoDTO em AlunoDTO
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

        // Inicializar a lista de cursos
        this.cursos = new ArrayList<>();

        // Adicionar cada curso à lista de cursos
        for (Curso curso : aluno.getCursos()) {
            this.cursos.add(new CursoDTO(curso));
        }
    }
}
