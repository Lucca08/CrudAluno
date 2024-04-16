package com.example.CrudAlunos.model;

import org.checkerframework.checker.units.qual.N;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Professor {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "professor_id")
    @NotNull(message = "O campo idDoProfessor é obrigatório")
    private long idDoProfessor;
    
    @Column(nullable = false)
    @NotBlank(message = "O campo nome é obrigatório")
    private String nome;

}
