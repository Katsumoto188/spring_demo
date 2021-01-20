package com.example.demo.utils.db;

import com.example.demo.model.domain.AlbumDomain;
import com.example.demo.model.domain.complete.AlbumCompleteDomain;
import com.example.demo.model.gateway.AlbumGateway;
import com.example.demo.model.gateway.PhotoGateway;
import com.example.demo.model.gateway.response.AlbumResponseGateway;
import com.example.demo.model.request.AlbumRequest;
import com.example.demo.model.request.complete.AlbumCompleteRequest;
import com.example.demo.model.response.AlbumResponse;
import com.example.demo.model.response.complete.AlbumCompleteResponse;
import com.example.demo.utils.gateway.MapperGateway;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class ConverterAlbumDB {

    public static AlbumDomain convertAlbumRequestToDomain(AlbumRequest album){
        return AlbumDomain.builder()
            .userId(new ObjectId(album.getUserId()))
            .title(album.getTitle())
            .build();
    }

    public static AlbumDomain convertAlbumGatewayToDomain(AlbumGateway album)
    {
        return AlbumDomain.builder()
                .title(album.getTitle())
                .build();
    }

    public static AlbumCompleteDomain convertAlbumToCompleteDomain(AlbumResponseGateway album)
    {
        return AlbumCompleteDomain
                .builder()
                .title(album.getTitle())
                .photos(MapperGateway.mapToCompletePhotoDomainList(album.getPhotoGateways()))
                .build();
    }



    public static AlbumCompleteDomain convertAlbumToCompleteDomain(AlbumGateway album, List<PhotoGateway> photos)
    {
        return AlbumCompleteDomain
                .builder()
                .title(album.getTitle())
                .photos(MapperGateway.mapToCompletePhotoDomainList(photos))
                .build();
    }

    public static AlbumCompleteDomain convertToAlbumCompleteDomain(AlbumCompleteRequest albumCompleteRequest)
    {
        return AlbumCompleteDomain
                .builder()
                .title(albumCompleteRequest.getTitle())
                .photos(MapperDB.mapPhotoCompleteDomainList(albumCompleteRequest.getPhotos()))
                .build();
    }
}
