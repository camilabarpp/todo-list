package camilabarpp.todolistjava.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tasks")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 100)
    private String description;
    @Column(length = 100, nullable = false)
    private Boolean completed;
    @Column(length = 20, nullable = false)
    private String weekDay;
}

