package camilabarpp.todolistjava.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {
    @NotBlank(message = "Name is mandatory")
    @NotNull(message = "Name is mandatory")
    @NotEmpty(message = "Name is mandatory")
    private String name;
    private String description;
    private Boolean completed;
    @NotBlank(message = "Week day is mandatory")
    @NotNull(message = "Week day is mandatory")
    private String weekDay;

    public Boolean getCompleted() {
        return this.completed = false;
    }
}
