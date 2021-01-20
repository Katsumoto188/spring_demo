package com.example.demo.model.request.complete;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class TodoCompleteRequest {
    private String id;
    private String title;
    private Boolean completed;
}
