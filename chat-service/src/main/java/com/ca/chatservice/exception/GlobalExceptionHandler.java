package com.ca.chatservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Mono<ResponseEntity<Map<String,String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((e)->{
            errors.put(e.getField(),e.getDefaultMessage());
        });
        return Mono.just(ResponseEntity.badRequest().body(errors));
    }

    @ExceptionHandler(BadRequestException.class)
    public Mono<ResponseEntity<Map<String,String>>> handleBadRequestException(BadRequestException ex){
        Map<String,String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return Mono.just(ResponseEntity.badRequest().body(error));
    }

    @ExceptionHandler(UnAuthorizedUserException.class)
    public Mono<ResponseEntity<Map<String,String>>> handleUnAuthorizedUserException(UnAuthorizedUserException ex){
        Map<String,String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return Mono.just(ResponseEntity.status(HttpStatus.FORBIDDEN).body(error));
    }

    @ExceptionHandler(ConversationNotFoundException.class)
    public Mono<ResponseEntity<Map<String,String>>> handleConversationNotFoundException(ConversationNotFoundException ex){
        Map<String,String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(error));
    }
}
