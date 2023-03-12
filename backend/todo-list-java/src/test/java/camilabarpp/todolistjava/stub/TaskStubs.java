package camilabarpp.todolistjava.stub;

import camilabarpp.todolistjava.model.TaskEntity;
import camilabarpp.todolistjava.model.TaskRequest;
import camilabarpp.todolistjava.model.TaskResponse;

public class TaskStubs {

    public static TaskEntity taskEntity() {
        return TaskEntity.builder()
                .id(1L)
                .name("Pagar a luz")
                .description("Entrar no site e gerar o boleto")
                .completed(false)
                .weekDay("Terça-feira")
                .build();
    }

    public static TaskResponse taskResponse() {
        return TaskResponse.builder()
                .id(1L)
                .name("Pagar a luz")
                .description("Entrar no site e gerar o boleto")
                .completed(false)
                .weekDay("Terça-feira")
                .build();
    }

    public static TaskRequest taskRequest() {
        return TaskRequest.builder()
                .name("Pagar a luz")
                .description("Entrar no site e gerar o boleto")
                .completed(false)
                .weekDay("Terça-feira")
                .build();
    }

    public static TaskRequest invalidTaskEntity() {
        return TaskRequest.builder()
                .name("")
                .description("")
                .completed(null)
                .weekDay("")
                .build();
    }
}
