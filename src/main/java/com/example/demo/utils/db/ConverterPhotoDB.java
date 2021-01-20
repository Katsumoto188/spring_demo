package com.example.demo.utils.db;

import com.example.demo.model.domain.PhotoDomain;
import com.example.demo.model.domain.complete.PhotoCompleteDomain;
import com.example.demo.model.gateway.PhotoGateway;
import com.example.demo.model.request.PhotoRequest;
import com.example.demo.model.request.complete.PhotoCompleteRequest;
import com.example.demo.model.response.PhotoResponse;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component

public class ConverterPhotoDB {

    public static PhotoDomain convertPhotoRequestToDomain (PhotoRequest photo){
        return PhotoDomain.builder()
                .albumId(new ObjectId(photo.getAlbumId()))
                .title(photo.getTitle())
                .url(photo.getUrl())
                .thumbnailUrl(photo.getThumbnailUrl())
                .build();
    }

    public static PhotoResponse convertPhotoDomainToResponse (PhotoDomain photo){
        return PhotoResponse.builder()
                .albumId(photo.getAlbumId().toHexString())
                .id(photo.getId().toHexString())
                .title(photo.getTitle())
                .url(photo.getUrl())
                .thumbnailUrl(photo.getThumbnailUrl())
                .build();
    }

    public static PhotoDomain convertPhotoGatewayToDomain(PhotoGateway photo)
    {
        return PhotoDomain
                .builder()
                .title(photo.getTitle())
                .url(photo.getUrl())
                .thumbnailUrl(photo.getThumbnailUrl())
                .build();
    }

    public static PhotoCompleteDomain convertPhotoGatewayToCompleteDomain(PhotoGateway photo)
    {
        return PhotoCompleteDomain
                .builder()
                .title(photo.getTitle())
                .url(photo.getUrl())
                .thumbnailUrl(photo.getThumbnailUrl())
                .build();
    }

    public static PhotoCompleteDomain convertToPhotoCompleteDomain(PhotoCompleteRequest photoCompleteRequest)
    {
        return PhotoCompleteDomain
                .builder()
                .thumbnailUrl(photoCompleteRequest.getThumbnailUrl())
                .url(photoCompleteRequest.getUrl())
                .title(photoCompleteRequest.getTitle())
                .build();
    }
}
