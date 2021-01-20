package com.example.demo.model.domain.complete;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder

public class AlbumCompleteDomain {

    private String title;

    private List<PhotoCompleteDomain> photos;
}
