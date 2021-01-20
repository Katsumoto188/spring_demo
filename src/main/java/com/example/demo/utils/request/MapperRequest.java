package com.example.demo.utils.request;

import com.example.demo.model.gateway.CommentGateway;
import com.example.demo.model.gateway.PhotoGateway;
import com.example.demo.model.gateway.TodoGateway;
import com.example.demo.model.gateway.response.AlbumResponseGateway;
import com.example.demo.model.gateway.response.PostResponseGateway;
import com.example.demo.model.gateway.response.UserResponseGateway;
import com.example.demo.model.request.complete.*;
import com.example.demo.model.response.complete.TodoCompleteResponse;

import java.util.List;
import java.util.stream.Collectors;

public class MapperRequest {

    public static List<UserCompleteRequest> mapUserCompleteRequestList(List<UserResponseGateway> userResponseGateways)
    {
        return userResponseGateways
                .stream()
                .map(ConverterRequest::convertToUserCompleteRequest)
                .collect(Collectors.toList());
    }

    public static List<PhotoCompleteRequest> mapPhotoCompleteRequestList(List<PhotoGateway> photoGateways)
    {
        return photoGateways
                .stream()
                .map(ConverterRequest::convertToPhotoCompleteRequest)
                .collect(Collectors.toList());
    }

    public static List<CommentCompleteRequest> mapCommentCompleteRequestList(List<CommentGateway> commentGateways)
    {
        return commentGateways
                .stream()
                .map(ConverterRequest::convertToCommentCompleteRequest)
                .collect(Collectors.toList());
    }

    public static List<PostCompleteRequest> mapPostCompleteRequestList(List<PostResponseGateway> postResponseGateways)
    {
        return postResponseGateways
                .stream()
                .map(ConverterRequest::convertToPostCompleteRequest)
                .collect(Collectors.toList());
    }

    public static List<TodoCompleteRequest> mapTodoCompleteResponseList(List<TodoGateway> todoGateways)
    {
        return todoGateways
                .stream()
                .map(ConverterRequest::convertToTodoCompleteRequest)
                .collect(Collectors.toList());
    }

    public static List<AlbumCompleteRequest> mapAlbumCompleteRequestList(List<AlbumResponseGateway> albumResponseGateways)
    {
        return albumResponseGateways
                .stream()
                .map(ConverterRequest::convertToAlbumCompleteRequest)
                .collect(Collectors.toList());
    }
}
