package camilabarpp.todolistjava.service;

import camilabarpp.todolistjava.model.TaskMapper;
import camilabarpp.todolistjava.model.TaskRequest;
import camilabarpp.todolistjava.model.TaskResponse;
import camilabarpp.todolistjava.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static camilabarpp.todolistjava.model.TaskMapper.*;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskResponse findById(Long id) {
        return entityToRespopnse(taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found" + id)));
    }

    public List<TaskResponse> findAll() {
        return taskRepository.findAll()
                .stream()
                .map(TaskMapper::entityToRespopnse)
                .collect(Collectors.toList());
    }

    public TaskResponse save(TaskRequest taskRequest) {
        return entityToRespopnse(taskRepository.save(requestToEntity(taskRequest)));
    }

    public TaskResponse update(Long id, TaskRequest taskRequest) {
        return entityToRespopnse(
                taskRepository.findById(id)
                        .map(taskEntity -> {
                            taskEntity.setCompleted(taskRequest.getCompleted());
                            return taskRepository.save(taskEntity);
                        })
                        .orElseThrow(() -> new RuntimeException("Task not found" + id))
        );
    }


    public void updateCompleted(Long id, Boolean completed) {
       taskRepository.findById(id)
                    .map(taskEntity -> {
                        taskEntity.setCompleted(completed);
                        return taskRepository.save(taskEntity);
                    })
                    .orElseThrow(() -> new RuntimeException("Task not found" + id));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public void deleteAllTasks() {
        taskRepository.deleteAll();
    }
}
