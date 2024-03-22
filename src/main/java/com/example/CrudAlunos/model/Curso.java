package com.example.CrudAlunos.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Curso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curso_id", nullable = false)
    private long id;
    
    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;
    
    @ManyToMany
    @JoinTable(
        name = "ALUNO_CURSO",
        joinColumns = @JoinColumn(name = "curso_id"),
        inverseJoinColumns = @JoinColumn(name = "aluno_id")
    )
    private List<Aluno> alunos = new ArrayList<>();
    
 
}
