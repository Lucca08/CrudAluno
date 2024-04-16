package com.example.CrudAlunos.model;

import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Aluno {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aluno_id")
    private Long id;
    
    @Column(nullable = false)
    @NotBlank(message = "O campo nome é obrigatório")
    private String nome;

    @Column(nullable = false)
    @NotBlank(message = "O campo cpf é obrigatório")
    @Size(min = 11, max = 11, message = "O CPF deve ter exatamente 11 caracteres")
    private String cpf;
    
    @Column(nullable = false)
    @NotBlank(message = "O campo matrícula é obrigatório")
    private String matricula;
    
    @JoinTable(name = "aluno_curso", joinColumns = @JoinColumn(name = "aluno_id"), inverseJoinColumns = @JoinColumn(name = "curso_id"))
    @ManyToMany
    private List<Curso> cursos = new ArrayList<>();
    
    public List<Curso> getCursos() {
        return this.cursos;
    }
    


}


