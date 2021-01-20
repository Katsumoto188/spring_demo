package com.example.demo.utils.db;

import com.example.demo.model.domain.PostDomain;
import com.example.demo.model.domain.complete.PostCompleteDomain;
import com.example.demo.model.gateway.CommentGateway;
import com.example.demo.model.gateway.PostGateway;
import com.example.demo.model.gateway.response.PostResponseGateway;
import com.example.demo.model.request.PostRequest;
import com.example.demo.model.request.complete.PostCompleteRequest;
import com.example.demo.model.response.PostResponse;
import com.example.demo.utils.gateway.MapperGateway;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class ConverterPostDB {

    public static PostDomain convertPostRequestToDomain (PostRequest post){
        return PostDomain.builder()
                .userId(new ObjectId(post.getUserId()))
                .title(post.getTitle())
                .body(post.getBody())
                .build();
    }

    public static PostResponse convertPostDomainToResponse (PostDomain post){
        return PostResponse.builder()
                .userId(post.getUserId().toHexString())
                .id(post.getId().toHexString())
                .title(post.getTitle())
                .body(post.getBody())
                .build();
    }

    public static PostDomain convertPostGatewayToCompleteDomain(PostGateway postGateway)
    {
        return PostDomain
                .builder()
                .body(postGateway.getBody())
                .title(postGateway.getTitle())
                .build();
    }

    public static PostCompleteDomain convertPostResponseGatewayToCompleteDomain(PostResponseGateway postGateway)
    {
        return PostCompleteDomain
                .builder()
                .body(postGateway.getBody())
                .title(postGateway.getTitle())
                .comments(MapperGateway.mapToCompleteCommentDomainList(postGateway.getCommentGateways()))
                .build();
    }


    public static PostCompleteDomain convertToPostCompleteDomain(PostCompleteRequest postCompleteRequest)
    {
        return PostCompleteDomain
                .builder()
                .body(postCompleteRequest.getBody())
                .title(postCompleteRequest.getTitle())
                .comments(MapperDB.mapCommentCompleteDomainList(postCompleteRequest.getComments()))
                .build();
    }


    // Apagar
    public static PostCompleteDomain convertPostResponseGatewayToCompleteDomain(PostGateway postGateway, List<CommentGateway> commentGateways)
    {
        return PostCompleteDomain
                .builder()
                .body(postGateway.getBody())
                .title(postGateway.getTitle())
                .comments(MapperGateway.mapToCompleteCommentDomainList(commentGateways))
                .build();
    }
}
