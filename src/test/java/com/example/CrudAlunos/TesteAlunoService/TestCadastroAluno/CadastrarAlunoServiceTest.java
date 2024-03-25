// package com.example.CrudAlunos.TesteAlunoService.TestCadastroAluno;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.when;

// import java.util.Optional;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import com.example.CrudAlunos.dto.AlunoDTO;
// import com.example.CrudAlunos.model.Aluno;
// import com.example.CrudAlunos.model.Curso;
// import com.example.CrudAlunos.repository.AlunoRepository;
// import com.example.CrudAlunos.repository.CursoRepository;
// import com.example.CrudAlunos.service.AlunoService.CadastrarAluno.CadastrarAlunoService;
// import com.example.CrudAlunos.Stub.StubAlunos;
// import com.example.CrudAlunos.Stub.StubCurso;

// public class CadastrarAlunoServiceTest {
//     private CursoRepository cursoRepository;
//     private AlunoRepository alunoRepository;
//     private CadastrarAlunoService cadastrarAlunoService;

//     @BeforeEach
//     public void setUp() {
//         cursoRepository = mock(CursoRepository.class);
//         alunoRepository = mock(AlunoRepository.class);
//         cadastrarAlunoService = new CadastrarAlunoService();
//     }

//     @Test
// public void testCadastrarAluno_Success() throws Exception {
//     AlunoDTO alunoDTO = new AlunoDTO();
//     alunoDTO.setNome("Lucca");

//     Curso curso = StubCurso.createCursoStub();

//     when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));

//     Aluno alunoSalvo = StubAlunos.AlunoStub1();
//     when(alunoRepository.save(any(Aluno.class))).thenReturn(alunoSalvo);

//     alunoSalvo.getCursos().add(curso);

//     Aluno alunoCadastrado = cadastrarAlunoService.cadastrarAluno(alunoDTO);

//     assertEquals(StubAlunos.AlunoStub1().getNome(), alunoCadastrado.getNome());
//     assertEquals(curso.getId(), alunoCadastrado.getCursos().get(0).getId());
// }

// @Test
// public void testCadastrarAluno_CourseNotFound() {
//     AlunoDTO alunoDTO = new AlunoDTO();
//     alunoDTO.setNome("Maria");

//     when(cursoRepository.findById(1L)).thenReturn(Optional.empty());

//     Exception exception = org.junit.jupiter.api.Assertions.assertThrows(Exception.class, () -> {
//         cadastrarAlunoService.cadastrarAluno(alunoDTO);
//     });

//     assertEquals("Curso não encontrado", exception.getMessage());
// }
// }
