package com.example.demo.utils.db;

import com.example.demo.model.domain.CommentDomain;
import com.example.demo.model.domain.complete.CommentCompleteDomain;
import com.example.demo.model.gateway.CommentGateway;
import com.example.demo.model.request.CommentRequest;
import com.example.demo.model.request.complete.CommentCompleteRequest;
import com.example.demo.model.response.CommentResponse;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component

public class ConverterCommentDB {

    public static CommentDomain convertCommentRequestToDomain (CommentRequest comment){
        return CommentDomain.builder()
                .postId(new ObjectId(comment.getPostId()))
                .name(comment.getName())
                .email(comment.getEmail())
                .body(comment.getBody())
                .build();
    }

    public static CommentResponse convertCommentDomainToResponse (CommentDomain comment){
        return CommentResponse.builder()
                .postId(comment.getPostId().toHexString())
                .id(comment.getId().toHexString())
                .name(comment.getName())
                .email(comment.getEmail())
                .body(comment.getBody())
                .build();
    }

    public static CommentCompleteDomain convertCommentGatewayToCompleteDomain(CommentGateway commentGateway)
    {
        return CommentCompleteDomain
                .builder()
                .body(commentGateway.getBody())
                .name(commentGateway.getName())
                .email(commentGateway.getEmail())
                .build();
    }

    public static CommentDomain convertToCommentGateway(CommentGateway commentGateway)
    {
        return CommentDomain
                .builder()
                .email(commentGateway.getEmail())
                .body(commentGateway.getBody())
                .name(commentGateway.getName())
                .build();
    }

    public static CommentCompleteDomain convertCommentCompleteRequestToCommentCompleteDomain(CommentCompleteRequest commentCompleteRequest)
    {
        return CommentCompleteDomain
                .builder()
                .email(commentCompleteRequest.getEmail())
                .body(commentCompleteRequest.getBody())
                .name(commentCompleteRequest.getName())
                .build();
    }
}
