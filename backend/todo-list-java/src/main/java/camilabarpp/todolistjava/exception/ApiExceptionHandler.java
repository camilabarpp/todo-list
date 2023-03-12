package camilabarpp.todolistjava.exception;

import camilabarpp.todolistjava.exception.errorresponse.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ApiExceptionHandler extends DefaultResponseErrorHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse notFoundException(NotFoundException e) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .field(NOT_FOUND.name())
                .parameter(e.getClass().getSimpleName())
                .build();
    }

    //Erro para valores nulos
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorResponse nullPointerException(NullPointerException e) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .field(INTERNAL_SERVER_ERROR.name())
                .parameter(e.getClass().getSimpleName())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage())
                .field(BAD_REQUEST.name())
                .parameter(exception.getClass().getSimpleName())
                .build();
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(METHOD_NOT_ALLOWED)
    public ErrorResponse httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .field(METHOD_NOT_ALLOWED.name())
                .parameter(exception.getClass().getSimpleName())
                .build();
    }

    //TODO - Fazer um teste para esse erro
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse dataIntegrityViolationException(DataIntegrityViolationException e) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message("Impossible insert do database, object with invalid size")
                .field(BAD_REQUEST.name())
                .parameter(e.getClass().getSimpleName())
                .build();
    }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleException(ResponseStatusException e) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .field(BAD_REQUEST.name())
                .parameter(e.getClass().getSimpleName())
                .build();
    }

    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse httpClientErrorException(HttpClientErrorException e) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message("Insert a valid CEP")
                .field(BAD_REQUEST.name())
                .parameter(e.getClass().getSimpleName())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception e) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .field(INTERNAL_SERVER_ERROR.name())
                .parameter(e.getClass().getSimpleName())
                .build();
    }
}
