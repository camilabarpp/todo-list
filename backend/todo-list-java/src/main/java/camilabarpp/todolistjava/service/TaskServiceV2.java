package camilabarpp.todolistjava.service;

import camilabarpp.todolistjava.exception.NotFoundException;
import camilabarpp.todolistjava.model.category.CategoryEntity;
import camilabarpp.todolistjava.model.task.TaskEntity;
import camilabarpp.todolistjava.model.task.TaskMapper;
import camilabarpp.todolistjava.model.task.TaskRequest;
import camilabarpp.todolistjava.model.task.TaskResponse;
import camilabarpp.todolistjava.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static camilabarpp.todolistjava.model.task.TaskMapper.entityToResponse;
import static camilabarpp.todolistjava.model.task.TaskMapper.requestToEntity;

@Service
@RequiredArgsConstructor
public class TaskServiceV2 {

    private final TaskRepository taskRepository;
    private final CategoryServiceV2 categoryServiceV2;

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

//    public List<TaskEntity> findByCategory(String categoryName) {
//        return taskRepository.findAllByCategoryCategoryNameContains(categoryName);
//    }

    public List<TaskEntity> findByDueDateBetween(LocalDate start, LocalDate end) {
        return taskRepository.findByDueDateBetween(start, end);
    }

    public List<TaskEntity> findByTaskTitle(String taskTitle) {
        return taskRepository.findAllByTaskTitleContaining(taskTitle);
    }

    public List<TaskEntity> findByDueDate(LocalDate date) {
        return taskRepository.findByDueDate(date);
    }

    public TaskResponse save(TaskRequest taskRequest) {
        if (taskRequest.getCategory() == null) {
            CategoryEntity withoutCategory = categoryServiceV2.findById(28L);
            taskRequest.setCategory(withoutCategory);
        } else {
            CategoryEntity category;
            category = categoryServiceV2.findByCategory(taskRequest.getCategory().getCategoryName());
            taskRequest.setCategory(category);
        }
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
//                            categoryServiceV2.findById(taskEntity.getCategory().getCategoryId())
//                                    .ifPresent(taskRequest::setCategory);
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
