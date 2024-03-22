package com.example.CrudAlunos.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.example.CrudAlunos.model.Aluno;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AlunoDTO {
    private Long id;
    private String nome;
    private List<CursoDTO> cursos;
    
    public AlunoDTO(Aluno aluno) {
        this.id = aluno.getId();
        this.nome = aluno.getNome();
        this.cursos = aluno.getCursos().stream()
                                        .map(CursoDTO::new)
                                        .collect(Collectors.toList());
    }

    public AlunoDTO(Long id) {
        this.id = id;
    }

}
