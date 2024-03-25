// package com.example.CrudAlunos.TesteAlunoService.TestSairDoCurso;

// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import java.util.Optional;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.springframework.boot.convert.PeriodFormat;

// import com.example.CrudAlunos.Stub.StubAlunos;
// import com.example.CrudAlunos.Stub.StubCurso;
// import com.example.CrudAlunos.dto.AlunoDTO;
// import com.example.CrudAlunos.dto.CursoDTO;
// import com.example.CrudAlunos.model.Aluno;
// import com.example.CrudAlunos.model.Curso;
// import com.example.CrudAlunos.repository.AlunoRepository;
// import com.example.CrudAlunos.repository.CursoRepository;
// import com.example.CrudAlunos.service.AlunoService.SairDoCurso.SairDoCursoService;

// import jakarta.persistence.PersistenceContext;

// public class SairDoCursoServiceTest {

//     @Mock
//     private AlunoRepository alunoRepository;

//     @Mock
//     private CursoRepository cursoRepository;

//     @InjectMocks
//     private SairDoCursoService sairDoCursoService;

//     @BeforeEach
//     public void setUp() {
//         MockitoAnnotations.openMocks(this);
//     }

//     @Test
//     public void testSairDoCursoComSucesso() {
//     Aluno aluno = StubAlunos.AlunoStub1();
//     Curso cursoMock = mock(Curso.class);
//     CursoDTO cursoDTO = new CursoDTO();
//     cursoDTO.setIdDoCurso(1L);

//     AlunoDTO alunoDTO = new AlunoDTO();
//     alunoDTO.setId(aluno.getId());

//     when(alunoRepository.findById(alunoDTO.getId())).thenReturn(Optional.of(aluno));
//     when(cursoRepository.findById(cursoDTO.getIdDoCurso())).thenReturn(Optional.of(cursoMock));

//     sairDoCursoService.sairDoCurso(alunoDTO, cursoDTO.getIdDoCurso());

//     verify(cursoRepository).save(cursoMock);
//     verify(cursoMock).getAlunos();
// }

    

//     @Test
//     public void testSairDoCursoCursoNaoEncontrado() {
//         Aluno aluno = StubAlunos.AlunoStub1();
//         AlunoDTO alunoDTO = new AlunoDTO();
//         alunoDTO.setId(aluno.getId());

//         when(alunoRepository.findById(alunoDTO.getId())).thenReturn(Optional.of(aluno));
//         when(cursoRepository.findById(1L)).thenReturn(Optional.empty());

//         assertThrows(RuntimeException.class, () -> {
//             sairDoCursoService.sairDoCurso(alunoDTO, 1L);
//         });
//     }
// }
