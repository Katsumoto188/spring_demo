package com.example.demo.model.domain.complete;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder

public class CommentCompleteDomain {

    private String name;
    private String email;
    private String body;

}
