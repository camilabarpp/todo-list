package camilabarpp.todolistjava.service;

import camilabarpp.todolistjava.exception.NotFoundException;
import camilabarpp.todolistjava.model.task.TaskMapper;
import camilabarpp.todolistjava.model.task.TaskRequest;
import camilabarpp.todolistjava.model.task.TaskResponse;
import camilabarpp.todolistjava.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static camilabarpp.todolistjava.model.task.TaskMapper.entityToResponse;
import static camilabarpp.todolistjava.model.task.TaskMapper.requestToEntity;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskResponse findById(Long id) {
        return entityToResponse(taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task " + id + " not found ")));
    }

    public List<TaskResponse> findAll() {
        return taskRepository.findAll()
                .stream()
                .map(TaskMapper::entityToResponse)
                .collect(Collectors.toList());
    }

    public TaskResponse save(TaskRequest taskRequest) {
        return entityToResponse(taskRepository.save(requestToEntity(taskRequest)));
    }

    public TaskResponse update(Long id, TaskRequest taskRequest) {
        return entityToResponse(
                taskRepository.findById(id)
                        .map(taskEntity -> {
                            taskEntity.setTaskTitle(taskRequest.getTaskTitle());
                            taskEntity.setDescription(taskRequest.getDescription());
                            taskEntity.setCompleted(taskRequest.getCompleted());
                            taskEntity.setDueDate(taskRequest.getDueDate());
                            taskEntity.setCategory(taskRequest.getCategory());
                            return taskRepository.save(taskEntity);
                        })
                        .orElseThrow(() -> new NotFoundException("Task " + id + " not found ")));

    }


    public void updateCompleted(Long id, Boolean completed) {
        taskRepository.findById(id)
                .map(taskEntity -> {
                    taskEntity.setCompleted(completed);
                    return taskRepository.save(taskEntity);
                })
                .orElseThrow(() -> new NotFoundException("Task " + id + " not found "));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public void deleteAllTasks() {
        taskRepository.deleteAll();
    }
}
