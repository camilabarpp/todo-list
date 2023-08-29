package camilabarpp.todolistjava.model.task.mapper;

import camilabarpp.todolistjava.model.task.entity.TaskEntity;
import camilabarpp.todolistjava.model.task.request.TaskRequest;
import camilabarpp.todolistjava.model.task.response.TaskResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TaskMapper {
    public static TaskResponse entityToResponse(TaskEntity taskEntity) {
        return TaskResponse.builder()
                .taskId(taskEntity.getTaskId())
                .taskTitle(taskEntity.getTaskTitle())
                .description(taskEntity.getDescription())
                .completed(taskEntity.getCompleted())
                .dueDate(taskEntity.getDueDate())
                .category(taskEntity.getCategory())
                .build();
    }

    public static TaskEntity requestToEntity(TaskRequest taskRequest) {
        return TaskEntity.builder()
                .taskTitle(taskRequest.getTaskTitle())
                .description(taskRequest.getDescription())
                .completed(taskRequest.getCompleted())
                .dueDate(taskRequest.getDueDate())
                .category(taskRequest.getCategory())
                .build();
    }

    public static TaskRequest entityToRequest(TaskEntity taskEntity) {
        return TaskRequest.builder()
                .taskTitle(taskEntity.getTaskTitle())
                .description(taskEntity.getDescription())
                .completed(taskEntity.getCompleted())
                .dueDate(taskEntity.getDueDate())
                .category(taskEntity.getCategory())
                .build();
    }

    public static TaskEntity responseToEntity(TaskResponse taskResponse) {
        return TaskEntity.builder()
                .taskTitle(taskResponse.getTaskTitle())
                .description(taskResponse.getDescription())
                .completed(taskResponse.getCompleted())
                .dueDate(taskResponse.getDueDate())
                .category(taskResponse.getCategory())
                .build();
    }
}
