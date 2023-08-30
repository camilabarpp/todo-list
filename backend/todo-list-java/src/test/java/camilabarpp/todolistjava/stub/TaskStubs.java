package camilabarpp.todolistjava.stub;

import camilabarpp.todolistjava.model.task.entity.TaskEntity;
import camilabarpp.todolistjava.model.task.request.TaskRequest;
import camilabarpp.todolistjava.model.task.response.TaskResponse;

import java.time.LocalDate;

public class TaskStubs {

    static final String invalidName;

    static {
        invalidName = "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an " +
                "unknown printer took a galley of type and scrambled" +
                " it to make a type specimen book. It has survived not only five centuries, but also the " +
                "leap into electronic typesetting, remaining essentially unchanged. It was popularised in " +
                "the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more " +
                "recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
    }


    public static TaskEntity taskEntity() {
        return TaskEntity.builder()
                .taskId(1L)
                .taskTitle("Pagar a luz")
                .description("Entrar no site e gerar o boleto")
                .completed(false)
                .dueDate(LocalDate.of(2021, 10, 20))
                .build();
    }

    public static TaskResponse taskResponse() {
        return TaskResponse.builder()
                .taskId(1L)
                .taskTitle("Pagar a luz")
                .description("Entrar no site e gerar o boleto")
                .completed(false)
                .dueDate(LocalDate.of(2021, 10, 20))
                .build();
    }

    public static TaskRequest taskRequest() {
        return TaskRequest.builder()
                .taskTitle("Pagar a luz")
                .description("Entrar no site e gerar o boleto")
                .completed(false)
                .dueDate(LocalDate.of(2021, 10, 20))
                .build();
    }

    public static TaskEntity invalidTaskEntity() {
        return TaskEntity.builder()
                .taskTitle("")
                .description("")
                .completed(null)
                .dueDate(null)
                .build();
    }

    public static TaskRequest invalidTaskRequestWithoutValidName() {
        return TaskRequest.builder()
                .taskTitle(invalidName)
                .description("Entrar no site e gerar o boleto")
                .completed(false)
                .dueDate(LocalDate.of(2021, 10, 20))
                .build();
    }
}
