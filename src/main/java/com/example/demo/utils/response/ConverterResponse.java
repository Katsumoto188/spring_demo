package com.example.demo.utils.response;

import com.example.demo.model.domain.AlbumDomain;
import com.example.demo.model.domain.complete.*;
import com.example.demo.model.response.AlbumResponse;
import com.example.demo.model.response.complete.*;
import com.example.demo.utils.db.ConverterUserDB;

public class ConverterResponse {

    public static UserCompleteResponse convertUserCompleteDomainToCompleteResponse(UserCompleteDomain userCompleteDomain)
    {
        return UserCompleteResponse
                .builder()
                .id(userCompleteDomain.getId())
                .email(userCompleteDomain.getEmail())
                .name(userCompleteDomain.getName())
                .phone(userCompleteDomain.getPhone())
                .username(userCompleteDomain.getUsername())
                .website(userCompleteDomain.getWebsite())

                .company(ConverterUserDB.convertUserCompanyDomainToCompanyDTO(userCompleteDomain.getCompany()))
                .address(ConverterUserDB.convertUserAddressDomainToAddressDTO(userCompleteDomain.getAddress()))

                .albums(MapperResponse.mapToAlbumCompleteResponseList(userCompleteDomain.getAlbums()))
                .posts(MapperResponse.mapToPostCompleteResponseList(userCompleteDomain.getPosts()))
                .todos(MapperResponse.mapToTodoCompleteResponseList(userCompleteDomain.getTodos()))
                .build();
    }

    public static AlbumResponse convertAlbumDomainToResponse (AlbumDomain album){
        return AlbumResponse.builder()
                .userId(album.getUserId().toHexString())
                .id(album.getId().toHexString())
                .title(album.getTitle())
                .build();
    }
    public static AlbumCompleteResponse convertAlbumToResponse(AlbumCompleteDomain albumCompleteDomain)
    {
        return AlbumCompleteResponse
                .builder()
//                .id(albumCompleteDomain.getId())
                .title(albumCompleteDomain.getTitle())
                .photos(MapperResponse.mapToPhotoCompleteResponseList(albumCompleteDomain.getPhotos()))
                .build();
    }

    public static PhotoCompleteResponse convertPhotoCompleteDomainToResponse(PhotoCompleteDomain photoCompleteDomain)
    {
        return PhotoCompleteResponse
                .builder()
//                .id(photoCompleteDomain.getId())
                .thumbnailUrl(photoCompleteDomain.getThumbnailUrl())
                .title(photoCompleteDomain.getTitle())
                .url(photoCompleteDomain.getUrl())
                .build();
    }

    public static PostCompleteResponse convertPostCompleteDomainToResponse(PostCompleteDomain postCompleteDomain)
    {
        return PostCompleteResponse
                .builder()
//                .id(postCompleteDomain.getId())
                .title(postCompleteDomain.getTitle())
                .body(postCompleteDomain.getBody())
                .comments(MapperResponse.mapToCommentCompleteResponseList(postCompleteDomain.getComments()))
                .build();
    }

    public static CommentCompleteResponse convertCommentCompleteDomainToResponse(CommentCompleteDomain commentCompleteDomain)
    {
        return CommentCompleteResponse
                .builder()
//                .id(commentCompleteDomain.getId())
                .email(commentCompleteDomain.getEmail())
                .body(commentCompleteDomain.getBody())
                .name(commentCompleteDomain.getName())
                .build();
    }

    public static TodoCompleteResponse convertToTodoCompleteResponse(TodoCompleteDomain todoCompleteDomain)
    {
        return TodoCompleteResponse
                .builder()
                .completed(todoCompleteDomain.getCompleted())
                .title(todoCompleteDomain.getTitle())
//                .id(todoCompleteDomain.getId())
                .build();
    }
}
