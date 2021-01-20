package com.example.demo.model.response.complete;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class PhotoCompleteResponse {

    private String id;
    private String title;
    private String url;
    private String thumbnailUrl;
}
