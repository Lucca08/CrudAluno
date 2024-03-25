package com.example.CrudAlunos.TestControllers.CursoController;

import com.example.CrudAlunos.dto.CursoDTO;
import com.example.CrudAlunos.dto.ProfessorDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
public class CursoControllerIntegrationTest {

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    public void testCriarProfessor() throws JsonProcessingException {
        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setNome("Professor Teste");
        professorDTO.setId(1L);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(objectMapper.writeValueAsString(professorDTO))
                .when()
                .post("/professores")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("nome", equalTo("Professor Teste"));
    }


    @Test
    public void testCriarProfessorSemNome() throws JsonProcessingException {
        ProfessorDTO professorDTO = new ProfessorDTO();

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(objectMapper.writeValueAsString(professorDTO))
                .when()
                .post("/professores")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", equalTo("Nome é obrigatório"));
    }


    @Test
    public void testCriarCurso() throws JsonProcessingException {
    CursoDTO cursoDTO = new CursoDTO();
    cursoDTO.setNome("Curso Teste");
    cursoDTO.setId(1L);
    cursoDTO.setDescricao("Descrição do curso teste");

    ProfessorDTO professorDTO = new ProfessorDTO();
    professorDTO.setNome("Professor Teste");
    professorDTO.setId(1L);
    cursoDTO.setProfessor(professorDTO);

    given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(objectMapper.writeValueAsString(cursoDTO))
    .when()
            .post("/cursos/criarcursos")
    .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("nome", equalTo("Curso Teste"));
}


    @Test
    public void testListarTodosCursos() {
        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when()
            .get("/cursos")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("$", hasSize(greaterThan(0)));
    }

}