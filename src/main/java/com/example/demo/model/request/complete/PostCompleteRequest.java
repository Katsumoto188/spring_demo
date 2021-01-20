package com.example.demo.model.request.complete;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class PostCompleteRequest {

    private String id;
    private String title;
    private String body;

    private List<CommentCompleteRequest> comments;
}
