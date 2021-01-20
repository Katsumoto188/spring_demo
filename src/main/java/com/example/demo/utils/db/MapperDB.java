package com.example.demo.utils.db;

import com.example.demo.model.domain.complete.*;
import com.example.demo.model.request.complete.*;

import java.util.List;
import java.util.stream.Collectors;

public class MapperDB {

    public static List<PhotoCompleteDomain> mapPhotoCompleteDomainList(List<PhotoCompleteRequest> photoCompleteRequest)
    {
        return photoCompleteRequest
                .stream()
                .map(ConverterPhotoDB::convertToPhotoCompleteDomain)
                .collect(Collectors.toList());
    }

    public static List<AlbumCompleteDomain> mapAlbumCompleteDomainList(List<AlbumCompleteRequest> albumCompleteRequest)
    {
        return albumCompleteRequest
                .stream()
                .map(ConverterAlbumDB::convertToAlbumCompleteDomain)
                .collect(Collectors.toList());
    }

    public static List<TodoCompleteDomain> mapTodoCompleteDomainList(List<TodoCompleteRequest> todoCompleteRequests)
    {
        return todoCompleteRequests
                .stream()
                .map(ConverterTodoDB::convertToTodoCompleteDomain)
                .collect(Collectors.toList());
    }

    public static List<CommentCompleteDomain> mapCommentCompleteDomainList(List<CommentCompleteRequest> commentCompleteRequests)
    {
        return commentCompleteRequests
                .stream()
                .map(ConverterCommentDB::convertCommentCompleteRequestToCommentCompleteDomain)
                .collect(Collectors.toList());
    }

    public static List<PostCompleteDomain> mapPostCompleteDomainList(List<PostCompleteRequest> postCompleteRequests)
    {
        return postCompleteRequests
                .stream()
                .map(ConverterPostDB::convertToPostCompleteDomain)
                .collect(Collectors.toList());
    }

    public static List<UserCompleteDomain> mapUserCompleteDomainList(List<UserCompleteRequest> userCompleteRequests)
    {
        return userCompleteRequests
                .stream()
                .map(ConverterUserDB::convertToUserCompleteDomain)
                .collect(Collectors.toList());
    }
}
