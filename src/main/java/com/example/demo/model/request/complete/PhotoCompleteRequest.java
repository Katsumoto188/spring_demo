package com.example.demo.model.request.complete;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class PhotoCompleteRequest {

    private String id;
    private String title;
    private String url;
    private String thumbnailUrl;
}
