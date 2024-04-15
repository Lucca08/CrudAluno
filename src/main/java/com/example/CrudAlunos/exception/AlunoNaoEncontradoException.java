package com.example.CrudAlunos.exception;

public class AlunoNaoEncontradoException extends RuntimeException {
    public AlunoNaoEncontradoException(String message) {
        super(message);
    }
}
