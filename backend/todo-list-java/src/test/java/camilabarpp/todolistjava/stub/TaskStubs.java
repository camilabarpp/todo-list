package camilabarpp.todolistjava.stub;

import camilabarpp.todolistjava.model.TaskEntity;
import camilabarpp.todolistjava.model.TaskRequest;
import camilabarpp.todolistjava.model.TaskResponse;

public class TaskStubs {

    static String invalidName = "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
            " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an " +
            "unknown printer took a galley of type and scrambled" +
            " it to make a type specimen book. It has survived not only five centuries, but also the " +
            "leap into electronic typesetting, remaining essentially unchanged. It was popularised in " +
            "the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more " +
            "recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";


    public static TaskEntity taskEntity() {
        return TaskEntity.builder()
                .id(1L)
                .name("Pagar a luz")
                .description("Entrar no site e gerar o boleto")
                .completed(false)
                .weekDay("Quarta-feira")
                .build();
    }

    public static TaskResponse taskResponse() {
        return TaskResponse.builder()
                .id(1L)
                .name("Pagar a luz")
                .description("Entrar no site e gerar o boleto")
                .completed(false)
                .weekDay("Quarta-feira")
                .build();
    }

    public static TaskRequest taskRequest() {
        return TaskRequest.builder()
                .name("Pagar a luz")
                .description("Entrar no site e gerar o boleto")
                .completed(false)
                .weekDay("Quarta-feira")
                .build();
    }

    public static TaskRequest invalidTaskRequest() {
        return TaskRequest.builder()
                .name("")
                .description("")
                .completed(null)
                .weekDay("")
                .build();
    }

    public static TaskRequest invalidTaskRequestWithoutValidName() {
        return TaskRequest.builder()
                .name(invalidName)
                .description("Entrar no site e gerar o boleto")
                .completed(false)
                .weekDay("Quarta-feira")
                .build();
    }
}
