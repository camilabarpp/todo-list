package camilabarpp.todolistjava.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class TaskResponse {
    private Long id;
    private String name;
    private String description;
    private String category;
    private LocalDate dueDate;
    private Boolean completed;
    private String weekDay;
}
