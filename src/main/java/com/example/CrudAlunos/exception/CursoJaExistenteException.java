package com.example.CrudAlunos.exception;

public class CursoJaExistenteException extends RuntimeException {
    public CursoJaExistenteException(String message) {
        super(message);
    }
}
