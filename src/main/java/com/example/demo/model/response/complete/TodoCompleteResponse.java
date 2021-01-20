package com.example.demo.model.response.complete;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class TodoCompleteResponse {
    private String id;
    private String title;
    private Boolean completed;
}
