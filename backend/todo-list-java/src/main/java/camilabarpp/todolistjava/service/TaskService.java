package camilabarpp.todolistjava.service;

import camilabarpp.todolistjava.exception.NotFoundException;
import camilabarpp.todolistjava.model.category.CategoryEntity;
import camilabarpp.todolistjava.model.task.entity.TaskEntity;
import camilabarpp.todolistjava.model.task.mapper.TaskMapper;
import camilabarpp.todolistjava.model.task.request.TaskRequest;
import camilabarpp.todolistjava.model.task.response.TaskResponse;
import camilabarpp.todolistjava.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static camilabarpp.todolistjava.model.task.mapper.TaskMapper.entityToResponse;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final CategoryService categoryService;

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

    public List<TaskEntity> findAllByCategoryName(String categoryName) {
        List<TaskEntity> taskList = taskRepository.findAllByCategoryName(categoryName);
        if (taskList.isEmpty()) {
            throw new NotFoundException("Category '" + categoryName + "' not found");
        }
        return taskList;
    }

    public List<TaskResponse> findByTaskTitle(String taskTitle) {
        return taskRepository.findAllByTaskTitleContaining(taskTitle)
                .stream()
                .map(TaskMapper::entityToResponse)
                .collect(Collectors.toList());
    }

    public List<TaskResponse> findByDueDateBetween(LocalDate start, LocalDate end) {
        return taskRepository.findByDueDateBetween(start, end)
                .stream()
                .map(TaskMapper::entityToResponse)
                .collect(Collectors.toList());
    }

    public List<TaskResponse> findByDueDate(LocalDate date) {
        return taskRepository.findByDueDate(date)
                .stream()
                .map(TaskMapper::entityToResponse)
                .collect(Collectors.toList());
    }

    public List<TaskResponse> findByCurrentWeek() {
        LocalDate currentDate = LocalDate.now();
        LocalDate endOfWeek = currentDate.plusDays(6);
        return taskRepository.findByDueDateBetween(currentDate, endOfWeek)
                .stream()
                .map(TaskMapper::entityToResponse)
                .collect(Collectors.toList());
    }

    public List<TaskResponse> findByCurrentMonth() {
        LocalDate currentDate = LocalDate.now();
        int daysInMonth = currentDate.lengthOfMonth();
        int remainingDays = daysInMonth - currentDate.getDayOfMonth();
        LocalDate endOfMonth = currentDate.plusDays(remainingDays);
        return taskRepository.findByDueDateBetween(currentDate, endOfMonth)
                .stream()
                .map(TaskMapper::entityToResponse)
                .collect(Collectors.toList());
    }

    public TaskEntity save(TaskEntity taskEntity) {
        var category = taskEntity.getCategory();
        CategoryEntity categoryEntity;
        if (category == null) {
            categoryEntity = categoryService.findByCategory("Sem categoria");
        } else {
            categoryEntity = categoryService.findByCategory(taskEntity.getCategory().getCategoryName());
        }
        taskEntity.setCategory(categoryEntity);
        categoryService.save(categoryEntity);
        return taskRepository.save(taskEntity);
    }

    public TaskResponse update(Long id, TaskRequest taskRequest) {
        return entityToResponse(
                taskRepository.findById(id)
                        .map(taskEntity -> {
                            taskEntity.setTaskTitle(taskRequest.getTaskTitle());
                            taskEntity.setDescription(taskRequest.getDescription());
                            taskEntity.setCompleted(taskRequest.getCompleted());
                            taskEntity.setDueDate(taskRequest.getDueDate());
                            return taskRepository.save(taskEntity);
                        })
                        .orElseThrow(() -> new NotFoundException("Task " + id + " not found ")));

    }

    public void updateCompleted(Long id, Boolean completed) {
        entityToResponse(taskRepository.findById(id)
                .map(taskEntity -> {
                    taskEntity.setCompleted(completed);
                    return taskRepository.save(taskEntity);
                })
                .orElseThrow(() -> new NotFoundException("Task " + id + " not found ")));
    }

    public void deleteAllById(List<Long> ids) {
        taskRepository.deleteAllById(ids);
    }

    public void deleteByID(Long id) {
        taskRepository.deleteById(id);
    }
}
