package camilabarpp.todolistjava.repository;

import camilabarpp.todolistjava.model.task.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findByDueDateBetween(LocalDate start, LocalDate end);
    List<TaskEntity> findByDueDate(LocalDate date);

    @Query(value = "SELECT t FROM TaskEntity t WHERE t.taskTitle LIKE %?1%")
    List<TaskEntity> findAllByTaskTitleContaining(String taskTitle);

}
