package com.example.CrudAlunos.exception;

import com.example.CrudAlunos.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlunoJaMatriculadoException.class)
    public ResponseEntity<ErrorResponse> handleAlunoJaMatriculadoException(AlunoJaMatriculadoException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Aluno já está matriculado no curso");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(AlunoNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleAlunoNaoEncontradoException(AlunoNaoEncontradoException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Aluno não encontrado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(CursoNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleCursoNaoEncontradoException(CursoNaoEncontradoException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Curso não encontrado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(ProfessorNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleProfessorNaoEncontradoException(ProfessorNaoEncontradoException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Professor não encontrado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> handleException(Throwable ex) {
        ErrorResponse errorResponse = new ErrorResponse("Ocorreu um erro interno no servidor");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
