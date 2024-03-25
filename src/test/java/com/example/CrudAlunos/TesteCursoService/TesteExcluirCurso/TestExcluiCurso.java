// package com.example.CrudAlunos.TesteCursoService.TesteExcluirCurso;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.*;

// import java.util.Optional;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;

// import com.example.CrudAlunos.model.Curso;
// import com.example.CrudAlunos.repository.CursoRepository;
// import com.example.CrudAlunos.service.CursoService.ExcluirCurso.ExcluirCursoService;

// public class TestExcluiCurso {

//     @Mock
//     private CursoRepository cursoRepository;

//     @InjectMocks
//     private ExcluirCursoService excluirCursoService;

//     @BeforeEach
//     public void setUp() {
//         MockitoAnnotations.initMocks(this);
//     }

//     @Test
//     public void testExcluirCursoExistente() {
//         // Defina o ID do curso que será excluído
//         Long idCurso = 1L;

//         // Simula a existência do curso com o ID fornecido
//         Curso cursoExistente = new Curso();
//         cursoExistente.setId(idCurso);

//         when(cursoRepository.findById(idCurso)).thenReturn(Optional.of(cursoExistente));

//         // Chama o método para excluir o curso
//         excluirCursoService.excluirCurso(idCurso);

//         // Verifica se o método delete foi chamado com o curso correto
//         verify(cursoRepository, times(1)).delete(cursoExistente);
//     }

//     @Test
//     public void testExcluirCursoInexistente() {
//         // Defina o ID de um curso que não existe no repositório
//         Long idCursoInexistente = 2L;

//         // Simula a ausência do curso com o ID fornecido
//         when(cursoRepository.findById(idCursoInexistente)).thenReturn(Optional.empty());

//         // Verifica se o método excluirCurso lança uma exceção quando o curso não é encontrado
//         assertThrows(RuntimeException.class, () -> {
//             excluirCursoService.excluirCurso(idCursoInexistente);
//         });
//     }
// }
