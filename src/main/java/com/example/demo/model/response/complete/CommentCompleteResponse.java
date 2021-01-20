package com.example.demo.model.response.complete;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class CommentCompleteResponse {
    private String id;
    private String name;
    private String email;
    private String body;
}
