package com.example.CrudAlunos.Stub;

import com.example.CrudAlunos.model.Aluno;

public class StubAlunos {
    public static Aluno AlunoStub1() {
        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNome("Lucca");
        aluno.setCpf("61725619016");
        aluno.setMatricula("123456");
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
