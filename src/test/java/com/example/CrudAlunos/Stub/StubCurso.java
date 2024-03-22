package com.example.CrudAlunos.Stub;

import com.example.CrudAlunos.model.Curso;

public class StubCurso {
        
    
    public static Curso createCursoStub() {
        Curso curso = new Curso();
        curso.setId(1L);
        curso.setNome("Curso de Artes");
        curso.setDescricao("Artistas renomados ensinam tecnicas de pintura e escultura");
        return curso;
    }

    public static Curso createCursoStub2() {
        Curso curso = new Curso();
        curso.setId(2L);
        curso.setNome("Curso de Musica");
        curso.setDescricao("Musicos renomados ensinam tecnicas de canto e instrumentos musicais");
        return curso;
    }

    public static Curso createCursoStub3() {
        Curso curso = new Curso();
        curso.setId(3L);
        curso.setNome("Curso de Danca");
        curso.setDescricao("Bailarinos renomados ensinam tecnicas de danca");
        return curso;
    }

    public static Curso createCursoStub4() {
        Curso curso = new Curso();
        curso.setId(4L);
        curso.setNome("Curso de Teatro");
        curso.setDescricao("Atores renomados ensinam tecnicas de interpretacao");
        return curso;
    }
    
}


