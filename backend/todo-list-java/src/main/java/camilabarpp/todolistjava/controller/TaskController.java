package camilabarpp.todolistjava.controller;

import camilabarpp.todolistjava.model.task.entity.TaskEntity;
import camilabarpp.todolistjava.model.task.request.TaskRequest;
import camilabarpp.todolistjava.model.task.response.TaskResponse;
import camilabarpp.todolistjava.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static camilabarpp.todolistjava.model.task.mapper.TaskMapper.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/v2/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public List<TaskResponse> findAll() {
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public TaskResponse findById(@PathVariable Long id) {
        return taskService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public TaskResponse save(@RequestBody TaskRequest taskRequest) {
        return entityToResponse(taskService.save(requestToEntity(taskRequest)));
    }

    @PutMapping("/{id}")
    public TaskResponse update(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        return taskService.update(id, taskRequest);
    }

    @PatchMapping("/{id}")
    public void updateCompleted(@PathVariable Long id, @RequestBody @Valid Map<String, Boolean> completed) {
        taskService.updateCompleted(id, completed.get("completed"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }

    @DeleteMapping("/deleteAllByIds")
    @ResponseStatus(NO_CONTENT)
    public void deleteAllByIds(@RequestParam List<Long> ids) {
        taskService.deleteAllById(ids);
    }

    @GetMapping(params = "categoryName")
    public List<TaskEntity> findByCategory(@RequestParam("categoryName") String categoryName) {
        return taskService.findAllByCategoryName(categoryName);
    }

    @GetMapping(params = "dueDate")
    public List<TaskResponse> findByDueDate(@RequestParam LocalDate dueDate) {
        return taskService.findByDueDate(dueDate);
    }

    @GetMapping("/currentWeek")
    public List<TaskResponse> findByCurrentWeek() {
        return taskService.findByCurrentWeek();
    }

    @GetMapping("currentMonth")
    public List<TaskResponse> findByCurrentMonth() {
        return taskService.findByCurrentMonth();
    }

    @GetMapping(params = {"start", "end"})
    public List<TaskResponse> findByDueDateBetween(@RequestParam LocalDate start, @RequestParam LocalDate end) {
        return taskService.findByDueDateBetween(start, end);
    }

    @GetMapping(params = "taskTitle")
    public List<TaskResponse> findByTaskTitle(@RequestParam String taskTitle) {
        return taskService.findByTaskTitle(taskTitle);
    }
}
