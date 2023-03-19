package camilabarpp.todolistjava.controller;

import camilabarpp.todolistjava.model.task.TaskEntity;
import camilabarpp.todolistjava.model.task.TaskRequest;
import camilabarpp.todolistjava.model.task.TaskResponse;
import camilabarpp.todolistjava.service.TaskServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/v2/tasks")
@RequiredArgsConstructor
public class TaskControllerV2 {

    private final TaskServiceV2 taskServiceV2;

    @GetMapping
    public List<TaskResponse> findAll() {
        return taskServiceV2.findAll();
    }

    @GetMapping("/{id}")
    public TaskResponse findById(@PathVariable Long id) {
        return taskServiceV2.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public TaskResponse save(@RequestBody TaskRequest taskRequest) {
        return taskServiceV2.save(taskRequest);
    }

    @PutMapping("/{id}")
    public TaskResponse update(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        return taskServiceV2.update(id, taskRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id) {
        taskServiceV2.delete(id);
    }

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    public void deleteAll() {
        taskServiceV2.deleteAll();
    }

//    @GetMapping(params = "category")
//    public List<TaskEntity> findByCategory(@RequestParam String categoryName) {
//        return taskServiceV2.findByCategory(categoryName);
//    }

    @GetMapping(params = "dueDate")
    public List<TaskEntity> findByDueDate(@RequestParam LocalDate dueDate) {
        return taskServiceV2.findByDueDate(dueDate);
    }

    @GetMapping(params = {"start", "end"})
    public List<TaskEntity> findByDueDateBetween(@RequestParam LocalDate start, @RequestParam LocalDate end) {
        return taskServiceV2.findByDueDateBetween(start, end);
    }

    @GetMapping(params = "taskTitle")
    public List<TaskEntity> findByTaskTitle(@RequestParam String taskTitle) {
        return taskServiceV2.findByTaskTitle(taskTitle);
    }
}
