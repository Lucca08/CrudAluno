package com.example.CrudAlunos.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @Column(nullable = false)
    @NotNull(message = "O campo idDoCurso é obrigatório")
    private long idDoCurso;
    
    @Column(nullable = false)
    @NotBlank(message = "O campo nome é obrigatório")
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;
    
    @ManyToMany
    @JoinTable(name = "ALUNO_CURSO",joinColumns = @JoinColumn(name = "curso_id"),inverseJoinColumns = @JoinColumn(name = "aluno_id"))
    private List<Aluno> alunos;
    

    public List<Aluno> getAlunos() {
        if (alunos == null) {
            alunos = new ArrayList<>();
        }
        return alunos;
    }
 
}
