package com.example.demo.model.response.complete;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class AlbumCompleteResponse {

    private String id;
    private String title;

    private List<PhotoCompleteResponse> photos;
}
