package camilabarpp.todolistjava.model.task;

import camilabarpp.todolistjava.model.category.CategoryEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {
    @NotBlank(message = "Name is mandatory")
    @NotNull(message = "Name is mandatory")
    @NotEmpty(message = "Name is mandatory")
    private String taskTitle;
    private String description;
    private Boolean completed;
    @NotBlank(message = "Due date is mandatory")
    @NotEmpty(message = "Due date is mandatory")
    @NotNull(message = "Due date is mandatory")
    private LocalDate dueDate;

    private CategoryEntity category;

    public Boolean getCompleted() {
        return this.completed = false;
    }
}
