package com.example.demo.model.domain.complete;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder

public class PhotoCompleteDomain {

    private String title;
    private String url;
    private String thumbnailUrl;

}
