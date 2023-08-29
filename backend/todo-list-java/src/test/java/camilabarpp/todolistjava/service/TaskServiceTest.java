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

import java.util.List;
import java.util.Optional;

import static camilabarpp.todolistjava.model.task.mapper.TaskMapper.requestToEntity;
import static camilabarpp.todolistjava.model.task.mapper.TaskMapper.responseToEntity;
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
    void create_validRequest_shouldCreatePerson() {
        TaskRequest personRequest = new TaskRequest();

        when(repository.save(requestToEntity(personRequest))).thenReturn(requestToEntity(personRequest));

        TaskResponse personResponse = service.save(personRequest);

        verify(repository).save(requestToEntity(personRequest));
        assertEquals(requestToEntity(personRequest), responseToEntity(personResponse));
    }


    @Test
    @DisplayName("Deve lançar NullPointerException quanto estiver faltando dados para salvar uma tarefa")
    void save_ShouldThrowNullPointerException() {
        TaskRequest request = invalidTaskRequest();

        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NullPointerException.class, () -> service.save(request));
    }

    @Test
    @DisplayName("Deve lançar ConstraintViolationException quanto estiver faltando dados para salvar uma tarefa")
    void save_ShouldThrowConstraintViolationException() {
        TaskRequest request = invalidTaskRequest();

        when(repository.save(requestToEntity(request))).thenThrow(ConstraintViolationException.class);

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

        service.deleteTask(id);
        verify(repository, times(1)).deleteById(id);
    }


    @Test
    @DisplayName("Deve deletar todas as tarefas")
    void deleteAll_WithSuccess() {

        service.deleteAllTasks();

        verify(repository).deleteAll();
    }
}