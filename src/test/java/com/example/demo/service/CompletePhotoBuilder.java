package com.example.demo.service;


import com.example.demo.model.domain.PhotoDomain;
import com.example.demo.model.request.PhotoRequest;
import com.example.demo.model.response.PhotoResponse;
import org.bson.types.ObjectId;

public class CompletePhotoBuilder {

    public static PhotoResponse validPhoto()
    {
        return PhotoResponse
                .builder()
                .id("507f191e810c19729de860ea")
                .albumId("507f191e810c19729de860e2")
                .url("teste_url.com")
                .title("titulo")
                .thumbnailUrl("thumbnail.com")
                .build();
    }

    public static PhotoDomain validPhotoDomain()
    {
        return PhotoDomain
                .builder()
                .id(new ObjectId("507f191e810c19729de860ea"))
                .albumId(new ObjectId("507f191e810c19729de860e2"))
                .url("teste_url.com")
                .title("titulo")
                .thumbnailUrl("thumbnail.com")
                .build();
    }

    public static PhotoDomain validPhotoDomainWrongId()
    {
        return PhotoDomain
                .builder()
                .albumId(new ObjectId("507f191e810c19729de860e2"))
                .url("teste_url.com")
                .title("titulo")
                .thumbnailUrl("thumbnail.com")
                .build();
    }

    public static PhotoDomain validPhotoDomainWithoutAllIds()
    {
        return PhotoDomain
                .builder()
                .albumId(new ObjectId("507f191e810c19729de860e2"))
                .url("teste_url.com")
                .title("titulo")
                .thumbnailUrl("thumbnail.com")
                .build();
    }

    public static PhotoDomain validPhotoDomainWithoutOriginalsId()
    {
        return PhotoDomain
                .builder()
                .id(new ObjectId("507f191e810c19729de860ea"))
                .albumId(new ObjectId("507f191e810c19729de860e2"))
                .url("teste_url.com")
                .title("titulo")
                .thumbnailUrl("thumbnail.com")
                .build();
    }

    public static PhotoRequest validPhotoRequest()
    {
        return PhotoRequest
                .builder()
                .albumId("507f191e810c19729de860e2")
                .url("teste_url.com")
                .title("titulo")
                .thumbnailUrl("thumbnail.com")
                .build();
    }
}

/*

    private String albumId;
    private String id;
    private String title;
    private String url;
    private String thumbnailUrl;

 */