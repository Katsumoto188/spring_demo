package com.example.demo.model.request.complete;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class CommentCompleteRequest {
    private String id;
    private String name;
    private String email;
    private String body;
}
