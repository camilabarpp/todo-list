package camilabarpp.todolistjava.service;

import camilabarpp.todolistjava.exception.NotFoundException;
import camilabarpp.todolistjava.model.task.entity.TaskEntity;
import camilabarpp.todolistjava.model.task.request.TaskRequest;
import camilabarpp.todolistjava.model.task.response.TaskResponse;
import camilabarpp.todolistjava.repository.TaskRepository;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static camilabarpp.todolistjava.model.task.mapper.TaskMapper.requestToEntity;
import static camilabarpp.todolistjava.stub.CategoryStubs.createCategory;
import static camilabarpp.todolistjava.stub.TaskStubs.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class TaskServiceTest {

    @InjectMocks
    private TaskService service;

    @Mock
    private TaskRepository repository;

    @Mock
    private CategoryService categoryService;


    @Test
    @DisplayName("Deve mostrar todas as tarefas")
    void findAll_WithSuccess() {
        List<TaskResponse> taskExpected = List.of(taskResponse(), taskResponse());
        var request = List.of(taskEntity(), taskEntity());

        when(repository.findAll()).thenReturn(request);

        List<TaskResponse> response = service.findAll();

        assertEquals(2, response.size());
        assertEquals(taskExpected, response);
        verify(repository).findAll();
    }

    @Test
    @DisplayName("Deve mostrar uma tarefa")
    void findById_WithSuccess() {
        TaskResponse expected = taskResponse();
        var request = taskEntity();

        when(repository.findById(1L)).thenReturn(Optional.of(request));

        TaskResponse response = service.findById(1L);

        assertEquals(expected, response);
        verify(repository).findById(1L);
    }

    @Test
    @DisplayName("Deve lancar NotFoundException quando nao encontrar uma tarefa")
    void findById_ShouldThrowNotFoundException() {
        var taskEntity = new TaskEntity();
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.findById(1L));

        verify(repository, never()).save(taskEntity);
    }

    @Test
    @DisplayName("Deve mostrar todas as tarefas por categoria")
    void findAllByCategoryName_WithSuccess() {
        List<TaskEntity> taskExpected = List.of(taskEntity(), taskEntity());
        var request = List.of(taskEntity(), taskEntity());

        when(repository.findAllByCategoryName("category")).thenReturn(request);

        List<TaskEntity> response = service.findAllByCategoryName("category");

        assertEquals(2, response.size());
        assertEquals(taskExpected, response);
        verify(repository).findAllByCategoryName("category");
    }

    @Test
    @DisplayName("Deve lançar NotFoundException quando não encontrar uma categoria")
    void findAllByCategoryName_ShouldThrowNotFoundException() {
        var taskEntity = new TaskEntity();
        when(repository.findAllByCategoryName("category")).thenReturn(List.of());

        assertThrows(NotFoundException.class, () -> service.findAllByCategoryName("category"));

        verify(repository, never()).save(taskEntity);
    }

    @Test
    @DisplayName("Deve atualizar uma tarefa")
    void update_ShouldUpdatePersonSuccessfully() {
        Long id = 1L;
        TaskRequest taskRequest = taskRequest();
        TaskResponse taskResponse = taskResponse();
        TaskEntity taskEntity = taskEntity();

        when(repository.findById(id)).thenReturn(Optional.of(taskEntity));
        when(repository.save(taskEntity)).thenReturn(taskEntity);

        TaskResponse actualPersonResponse = service.update(id, taskRequest);

        assertEquals(taskResponse, actualPersonResponse);
        verify(repository).findById(id);
        verify(repository).save(taskEntity);
    }

    @Test
    @DisplayName("Deve lançar NotFoundException quando não encontrar uma pessoa para atualizar")
    void update_personNotFound_shouldThrowNotFoundException() {
        long invalidId = 2L;
        TaskRequest personRequest = new TaskRequest();

        when(repository.findById(invalidId))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.update(invalidId, personRequest));
    }


    @Test
    @DisplayName("Deve salvar uma tarefa")
    void save_validRequest_shouldCreatePerson() {
        TaskEntity taskEntity = new TaskEntity();

        when(repository.save(taskEntity)).thenReturn(taskEntity);

        var save = service.save(taskEntity);

        verify(repository).save(taskEntity);
        assertEquals(taskEntity, save);
    }

    @Test
    @DisplayName("Deve salvar uma tarefa com categoria")
    void save_validRequestWithCategory_shouldCreatePerson() {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setCategory(createCategory());

        when(repository.save(taskEntity)).thenReturn(taskEntity);

        var save = service.save(taskEntity);

        verify(repository).save(taskEntity);
        assertEquals(taskEntity, save);
    }

    @Test
    @DisplayName("Deve salvar uma tarefa com a categoria default")
    void save_validRequestWithDefaultCategory_shouldCreatePerson() {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setCategory(null);

        when(repository.save(taskEntity)).thenReturn(taskEntity);
        when(categoryService.findByCategory("Sem categoria")).thenReturn(createCategory());

        var save = service.save(taskEntity);

        verify(repository).save(taskEntity);
        assertEquals(taskEntity, save);
    }

    @Test
    @DisplayName("Deve lançar NullPointerException quanto estiver faltando dados para salvar uma tarefa")
    void save_ShouldThrowNullPointerException() {
        TaskEntity request = TaskEntity
                .builder()
                .description("description")
                .completed(true)
                .dueDate(LocalDate.now())
                .build();

        when(repository.save(request)).thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () -> service.save(request));
    }

    @Test
    @DisplayName("Deve lançar ConstraintViolationException quanto estiver faltando dados para salvar uma tarefa")
    void save_ShouldThrowConstraintViolationException() {
        TaskEntity request = invalidTaskEntity();

        when(repository.save(request)).thenThrow(ConstraintViolationException.class);

        assertThrows(ConstraintViolationException.class, () -> service.save(request));
    }

    @Test
    @DisplayName("Deve deletar uma tarefa")
    void deleteById_validId_shouldDeletePerson() {
        Long id = 1L;
        TaskEntity taskEntity = taskEntity();

        when(repository.save(taskEntity)).thenReturn(taskEntity);
        when(repository.findById(id)).thenReturn(Optional.of(taskEntity));

        doNothing().when(repository).deleteById(id);

        service.deleteByID(id);
        verify(repository, times(1)).deleteById(id);
        assertEquals(0, repository.findAll().size());
    }

    @Test
    @DisplayName("Deve deletar todas as tarefas")
    void deleteAll_WithSuccess() {
        when(repository.save(any(TaskEntity.class))).thenReturn(any(TaskEntity.class));
        when(repository.findById(1L)).thenReturn(Optional.of(taskEntity()));
        when(repository.findById(2L)).thenReturn(Optional.of(taskEntity()));

        service.deleteAllById(List.of(1L, 2L));

        verify(repository).deleteAllById(List.of(1L, 2L));
        assertEquals(0, repository.findAll().size());
    }
}