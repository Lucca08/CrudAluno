
    package com.example.CrudAlunos.Stub;
    import com.example.CrudAlunos.model.Aluno;
    import com.example.CrudAlunos.model.Curso;
    
    import java.util.ArrayList;
    import java.util.List;
    
    public class StubAlunos {
        public static Aluno AlunoStub1() {
            Aluno aluno = new Aluno();
            aluno.setId(1L);
            aluno.setNome("Lucca");
            aluno.setCpf("61725619016");
            aluno.setMatricula("123456");
    
            // Criar um curso simulado
            Curso curso = StubCurso.createCursoStub();
    
            // Criar uma lista contendo o curso
            List<Curso> cursos = new ArrayList<>();
            cursos.add(curso);
    
            // Associar a lista de cursos ao aluno
            aluno.setCursos(cursos);
    
            return aluno;
        }
    
    
    public static Aluno AlunoStub2() {
        Aluno aluno = new Aluno();
        aluno.setId(2L);
        aluno.setNome("Jobson");
        aluno.setCpf("38678686030");
        aluno.setMatricula("12321412");
        return aluno;
    }

  

}
