package com.example.demo.model.request.complete;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class AlbumCompleteRequest {

    private String id;
    private String title;

    private List<PhotoCompleteRequest> photos;
}
