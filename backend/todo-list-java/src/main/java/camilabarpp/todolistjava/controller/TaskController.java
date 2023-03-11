package camilabarpp.todolistjava.controller;

import camilabarpp.todolistjava.model.TaskRequest;
import camilabarpp.todolistjava.model.TaskResponse;
import camilabarpp.todolistjava.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/v1/tasks")
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
        return taskService.save(taskRequest);
    }

    @PutMapping("/{id}")
    public TaskResponse update(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        return taskService.update(id, taskRequest);
    }

    @PatchMapping("/{id}")
    public void updateCompleted(@PathVariable Long id, @RequestBody Map<String, Boolean> completed) {
        taskService.updateCompleted(id, completed.get("completed"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    public void deleteAllTasks() {
        taskService.deleteAllTasks();
    }
}
