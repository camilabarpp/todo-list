package camilabarpp.todolistjava.model.task;

import camilabarpp.todolistjava.model.category.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class TaskResponse {
    private Long taskId;
    private String taskTitle;
    private String description;
    private CategoryEntity category;
    private LocalDate dueDate;
    private Boolean completed;
}
