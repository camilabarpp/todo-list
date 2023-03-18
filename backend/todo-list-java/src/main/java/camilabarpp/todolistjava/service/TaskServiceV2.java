package camilabarpp.todolistjava.service;

import camilabarpp.todolistjava.exception.NotFoundException;
import camilabarpp.todolistjava.model.TaskMapper;
import camilabarpp.todolistjava.model.TaskRequest;
import camilabarpp.todolistjava.model.TaskResponse;
import camilabarpp.todolistjava.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static camilabarpp.todolistjava.model.TaskMapper.entityToResponse;
import static camilabarpp.todolistjava.model.TaskMapper.requestToEntity;

@Service
public class TaskServiceV2 {

    private TaskRepository taskRepository;

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
                            taskEntity.setName(taskRequest.getName());
                            taskEntity.setDescription(taskRequest.getDescription());
                            taskEntity.setCompleted(taskRequest.getCompleted());
                            taskEntity.setWeekDay(taskRequest.getWeekDay());
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

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    //TODO implementar o método deleteAll no frontend
    public void deleteAll() {
        taskRepository.deleteAll();
    }
}
