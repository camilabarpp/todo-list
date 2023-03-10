package camilabarpp.todolistjava.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TaskRequest {
    @NotBlank(message = "Name is mandatory")
    private String name;
    private String description;
    private Boolean completed;
    @NotBlank(message = "Week day is mandatory")
    private String weekDay;
}
