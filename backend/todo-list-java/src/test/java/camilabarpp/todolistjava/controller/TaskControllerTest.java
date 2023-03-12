package camilabarpp.todolistjava.controller;

import camilabarpp.todolistjava.model.TaskRequest;
import camilabarpp.todolistjava.repository.TaskRepository;
import camilabarpp.todolistjava.service.TaskService;
import camilabarpp.todolistjava.stub.TaskStubs;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static camilabarpp.todolistjava.stub.TaskStubs.taskEntity;
import static camilabarpp.todolistjava.stub.TaskStubs.taskRequest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        taskRepository.save(taskEntity());
    }

    @AfterEach
    void tearDown() {
        taskRepository.deleteAll();
    }

    @Test
    @DisplayName("Should return all tasks")
    void shouldFindAllTasksWithSuccess() throws Exception {
        MvcResult result = mvc.perform(get("/api/v1/tasks"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).contains("Quarta-feira");
        assertThat(content).contains("Entrar no site e gerar o boleto");
        assertThat(content).contains("Pagar a luz");
    }

    @Test
    @DisplayName("Should return a task by id")
    void shouldFindATaskByIdWithSuccess() throws Exception {
        var taskEntity = taskRepository.save(taskEntity());
        var id = taskEntity.getId();

        MvcResult result = mvc.perform(get("/api/v1/tasks/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).contains("Quarta-feira");
        assertThat(content).contains("Entrar no site e gerar o boleto");
        assertThat(content).contains("Pagar a luz");
    }

    @Test
    @DisplayName("Should throws NotFoundException when not found a taskEntity")
    void shouldThrowsNotFoundException_WhenNotFoundATaskEntity() throws Exception {
        MvcResult result = mvc.perform(get("/api/v1/tasks/1"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).contains("Task 1 not found ");
        assertThat(content).contains("NOT_FOUND");
        assertThat(content).contains("NotFoundException");
    }

    @Test
    @DisplayName("Should save a task")
    void shouldSaveATaskWithSuccess() throws Exception {
        MvcResult result = mvc.perform(post("/api/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(taskRequest())))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).contains("Quarta-feira");
        assertThat(content).contains("Entrar no site e gerar o boleto");
        assertThat(content).contains("Pagar a luz");
    }

    @Test
    @DisplayName("Should throws MethodArgumentNotValidException when thy save a invalid task")
    void shouldThrowsMethodArgumentNotValidException_WhenTrySaveAInvalidTask() throws Exception {
        MvcResult result = mvc.perform(post("/api/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(TaskStubs.invalidTaskRequest())))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).contains("is mandatory");
        assertThat(content).contains("BAD_REQUEST");
        assertThat(content).contains("MethodArgumentNotValidException");
    }

    @Test
    @DisplayName("Should throws DataIntegrityViolationException when thy save a invalid task name")
    void shouldThrowsMethodArgumentDataIntegrityViolationException_WhenTrySaveAInvalidTaskName() throws Exception {
        MvcResult result = mvc.perform(post("/api/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(TaskStubs.invalidTaskRequestWithoutValidName())))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).contains("Impossible insert do database, object with invalid size");
        assertThat(content).contains("BAD_REQUEST");
        assertThat(content).contains("DataIntegrityViolationException");
    }

    @Test
    @DisplayName("Should update a task with Success")
    void update() throws Exception {
        var taskEntity = taskRepository.save(taskEntity());
        var id = taskEntity.getId();

        MvcResult result = mvc.perform(put("/api/v1/tasks/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(taskRequest())))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).contains("Quarta-feira");
        assertThat(content).contains("Entrar no site e gerar o boleto");
        assertThat(content).contains("Pagar a luz");
    }

    @Test
    @DisplayName("Should throws NotFoundException when not found a taskEntity to update")
    void shouldThrowsNotFoundException_WhenNotFoundATaskEntityToUpdate() throws Exception {
        MvcResult result = mvc.perform(put("/api/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(taskRequest())))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).contains("Task 1 not found ");
        assertThat(content).contains("NOT_FOUND");
        assertThat(content).contains("NotFoundException");
    }

    @Test
    @DisplayName("Should update completed")
    void updateCompleted() throws Exception {
        var taskEntity = taskRepository.save(taskEntity());
        var id = taskEntity.getId();

        String request = mapper.writeValueAsString(TaskRequest.builder()
                .completed(true)
                .build());

        MvcResult result = mvc.perform(patch("/api/v1/tasks/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).isEqualTo(result.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("Should delete a task")
    void deleteTaskWithSuccess() throws Exception {
        var taskEntity = taskRepository.save(taskEntity());
        var id = taskEntity.getId();

        MvcResult result = mvc.perform(delete("/api/v1/tasks/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(taskRequest())))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();

        String json = result.getResponse().getContentAsString();

        assertThat(json).isEmpty();
    }

    @Test
    @DisplayName("Should delete all tasks")
    void deleteAllTasks() throws Exception {
        MvcResult result = mvc.perform(delete("/api/v1/tasks"))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();

        String json = result.getResponse().getContentAsString();

        //When
        assertThat(json).isEmpty();
    }
}