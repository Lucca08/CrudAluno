package com.example.CrudAlunos.model;

import java.util.HashSet;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Aluno {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Curso curso;
    
    @Column
    private String nome;
    
    @JoinTable(name = "aluno_curso", joinColumns = @JoinColumn(name = "aluno_id"), inverseJoinColumns = @JoinColumn(name = "curso_id"))
    @ManyToMany
    private List<Curso> cursos;	
}
