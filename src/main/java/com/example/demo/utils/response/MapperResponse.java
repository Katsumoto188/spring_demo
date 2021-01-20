package com.example.demo.utils.response;

import com.example.demo.model.domain.complete.*;
import com.example.demo.model.response.complete.*;
import com.example.demo.utils.db.ConverterTodoDB;

import java.util.List;
import java.util.stream.Collectors;

public class MapperResponse {

    public static List<UserCompleteResponse> mapToUserCompleteResponseList(List<UserCompleteDomain> userCompleteDomains)
    {
        return userCompleteDomains
                .stream()
                .map(ConverterResponse::convertUserCompleteDomainToCompleteResponse)
                .collect(Collectors.toList());
    }

    public static List<TodoCompleteResponse> mapToTodoCompleteResponseList(List<TodoCompleteDomain> todoCompleteDomains)
    {
        return todoCompleteDomains
                .stream()
                .map(ConverterTodoDB::convertToTodoCompleteResponse)
                .collect(Collectors.toList());
    }

    public static List<AlbumCompleteResponse> mapToAlbumCompleteResponseList(List<AlbumCompleteDomain> albumCompleteDomains)
    {
        return albumCompleteDomains
                .stream()
                .map(ConverterResponse::convertAlbumToResponse)
                .collect(Collectors.toList());
    }

    public static List<PhotoCompleteResponse> mapToPhotoCompleteResponseList(List<PhotoCompleteDomain> photoCompleteDomains)
    {
        return photoCompleteDomains
                .stream()
                .map(ConverterResponse::convertPhotoCompleteDomainToResponse)
                .collect(Collectors.toList());
    }

    public static List<PostCompleteResponse> mapToPostCompleteResponseList(List<PostCompleteDomain> postCompleteDomains)
    {
        return postCompleteDomains
                .stream()
                .map(ConverterResponse::convertPostCompleteDomainToResponse)
                .collect(Collectors.toList());
    }

    public static List<CommentCompleteResponse> mapToCommentCompleteResponseList(List<CommentCompleteDomain> commentCompleteDomains)
    {
        return commentCompleteDomains
                .stream()
                .map(ConverterResponse::convertCommentCompleteDomainToResponse)
                .collect(Collectors.toList());
    }
}
