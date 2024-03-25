// package com.example.CrudAlunos.TesteAlunoService.TestBuscarSeAlunoEstaCadastrado;

// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.when;

// import java.util.Optional;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import com.example.CrudAlunos.dto.AlunoDTO;
// import com.example.CrudAlunos.model.Aluno;
// import com.example.CrudAlunos.model.Curso;
// import com.example.CrudAlunos.repository.AlunoRepository;
// import com.example.CrudAlunos.service.AlunoService.BuscarSeAlunoEstaCadastrado.BuscarCadastroService;
// import com.example.CrudAlunos.Stub.StubAlunos;
// import com.example.CrudAlunos.Stub.StubCurso;

// public class BuscarCadastroServiceTest {

//     private AlunoRepository alunoRepository;
//     private BuscarCadastroService buscarCadastroService;

//     @BeforeEach
//     public void setUp() {
//         alunoRepository = mock(AlunoRepository.class);
//         buscarCadastroService = new BuscarCadastroService(alunoRepository);
//     }

//     @Test
//     public void testVerificarAlunoNoCurso_AlunoPresenteNoCurso() {
//         Aluno aluno = StubAlunos.AlunoStub1();

//         Curso curso = StubCurso.createCursoStub();

//         aluno.getCursos().add(curso);

//         when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));

//         assertTrue(buscarCadastroService.verificarAlunoNoCurso(new AlunoDTO(1L), 1L));
//     }

//     @Test
//     public void testVerificarAlunoNoCurso_AlunoNaoPresenteNoCurso() {
//         Aluno aluno = StubAlunos.AlunoStub1();

//         Curso curso = StubCurso.createCursoStub();

//         when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));

//         assertFalse(buscarCadastroService.verificarAlunoNoCurso(new AlunoDTO(1L), 2L));
//     }

//     @Test
//     public void testVerificarAlunoNoCurso_AlunoNaoEncontrado() {
//         when(alunoRepository.findById(1L)).thenReturn(Optional.empty());

//         assertFalse(buscarCadastroService.verificarAlunoNoCurso(new AlunoDTO(1L), 1L));
//     }
// }
