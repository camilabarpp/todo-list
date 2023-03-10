package camilabarpp.todolistjava.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TaskResponse {
    private Long id;
    private String name;
    private String description;
    private Boolean completed;
    private String weekDay;
}
