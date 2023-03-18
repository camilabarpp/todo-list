package camilabarpp.todolistjava.model;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TaskMapper {
    public static TaskResponse entityToResponse(TaskEntity taskEntity) {
        return TaskResponse.builder()
                .id(taskEntity.getId())
                .name(taskEntity.getName())
                .description(taskEntity.getDescription())
                .completed(taskEntity.getCompleted())
                .weekDay(taskEntity.getWeekDay())
                .build();
    }

    public static TaskEntity requestToEntity(TaskRequest taskRequest) {
        return TaskEntity.builder()
                .name(taskRequest.getName())
                .description(taskRequest.getDescription())
                .completed(taskRequest.getCompleted())
                .weekDay(taskRequest.getWeekDay())
                .build();
    }

    public static TaskEntity responseToEntity(TaskResponse taskResponse) {
        return TaskEntity.builder()
                .name(taskResponse.getName())
                .description(taskResponse.getDescription())
                .completed(taskResponse.getCompleted())
                .weekDay(taskResponse.getWeekDay())
                .build();
    }
}
