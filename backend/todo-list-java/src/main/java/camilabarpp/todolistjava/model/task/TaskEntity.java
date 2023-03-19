package camilabarpp.todolistjava.model.task;

import camilabarpp.todolistjava.model.category.CategoryEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tasksV2")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    @Column(length = 50, nullable = false)
    private String taskTitle;
    @Column(length = 100)
    private String description;
    @Column(length = 20, nullable = false)
    private LocalDate dueDate;
    @Column(length = 100, nullable = false)
    private Boolean completed;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
}

