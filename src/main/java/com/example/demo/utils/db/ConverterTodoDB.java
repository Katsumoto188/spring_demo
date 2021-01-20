package com.example.demo.utils.db;

import com.example.demo.model.domain.TodoDomain;
import com.example.demo.model.domain.complete.TodoCompleteDomain;
import com.example.demo.model.gateway.TodoGateway;
import com.example.demo.model.request.TodoRequest;
import com.example.demo.model.request.complete.TodoCompleteRequest;
import com.example.demo.model.response.TodoResponse;
import com.example.demo.model.response.complete.TodoCompleteResponse;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component

public class ConverterTodoDB {

    public static TodoDomain convertTodoRequestToDomain (TodoRequest todo){
        return TodoDomain.builder()
                .userId(new ObjectId(todo.getUserId()))
                .title(todo.getTitle())
                .completed(todo.getCompleted())
                .build();
    }

    public static TodoResponse convertTodoDomainToResponse (TodoDomain todo){
        return TodoResponse.builder()
                .id(todo.getId().toHexString())
                .userId(todo.getUserId().toHexString())
                .title(todo.getTitle())
                .completed(todo.getCompleted())
                .build();
    }

    public static TodoDomain convertTodoGatewayToTodoDomain(TodoGateway todoGateway)
    {
        return TodoDomain
                .builder()
                .completed(todoGateway.getCompleted())
                .title(todoGateway.getTitle())
                .build();
    }

    public static TodoCompleteDomain convertTodoGatewayToTodoCompleteDomain(TodoGateway todoGateway)
    {
        return TodoCompleteDomain
                .builder()
                .completed(todoGateway.getCompleted())
                .title(todoGateway.getTitle())
                .build();
    }

    public static TodoCompleteDomain convertToTodoCompleteDomain(TodoCompleteRequest todoCompleteDomain)
    {
        return TodoCompleteDomain
                .builder()
                .completed(todoCompleteDomain.getCompleted())
//                .id(todoCompleteDomain.getId())
                .title(todoCompleteDomain.getTitle())
                .build();
    }

    public static TodoCompleteResponse convertToTodoCompleteResponse(TodoCompleteDomain todoCompleteDomain)
    {
        return TodoCompleteResponse
                .builder()
                .completed(todoCompleteDomain.getCompleted())
//                .id(todoCompleteDomain.getId())
                .title(todoCompleteDomain.getTitle())
                .build();
    }
}
