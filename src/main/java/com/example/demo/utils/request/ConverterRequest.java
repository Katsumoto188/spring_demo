package com.example.demo.utils.request;

import com.example.demo.model.domain.user.UserAddressDomain;
import com.example.demo.model.domain.user.UserAddressGeoDomain;
import com.example.demo.model.domain.user.UserCompanyDomain;
import com.example.demo.model.dto.user.UserAddressDTO;
import com.example.demo.model.dto.user.UserAddressGeoDTO;
import com.example.demo.model.dto.user.UserCompanyDTO;
import com.example.demo.model.gateway.CommentGateway;
import com.example.demo.model.gateway.PhotoGateway;
import com.example.demo.model.gateway.TodoGateway;
import com.example.demo.model.gateway.response.AlbumResponseGateway;
import com.example.demo.model.gateway.response.PostResponseGateway;
import com.example.demo.model.gateway.response.UserResponseGateway;
import com.example.demo.model.gateway.user.UserAddressGateway;
import com.example.demo.model.gateway.user.UserAddressGeoGateway;
import com.example.demo.model.gateway.user.UserCompanyGateway;
import com.example.demo.model.request.complete.*;
import com.example.demo.model.response.complete.CommentCompleteResponse;
import com.example.demo.model.response.complete.TodoCompleteResponse;
import com.example.demo.utils.db.ConverterUserDB;
import com.example.demo.utils.response.MapperResponse;

import java.util.Objects;

public class ConverterRequest {

    public static UserAddressDTO convertToAddressDTO(UserAddressGateway address)
    {
        return UserAddressDTO
                .builder()
                .street(address.getStreet())
                .city(address.getCity())
                .suite(address.getSuite())
                .zipcode(address.getZipcode())
                .geo( (Objects.isNull(address.getGeo()) ? null:convertToAddressGeoDTO(address.getGeo())) )
                .build();
    }

    public static UserAddressGeoDTO convertToAddressGeoDTO(UserAddressGeoGateway geo)
    {
        return UserAddressGeoDTO
                .builder()
                .lat(Double.parseDouble(geo.getLat()))
                .lng(Double.parseDouble(geo.getLng()))
                .build();
    }

    private static UserCompanyDTO convertToUserCompanyDTO (UserCompanyGateway company){
        return UserCompanyDTO.builder()
                .name(company.getName())
                .catchPhrase(company.getCatchPhrase())
                .bs(company.getBs())
                .build();
    }

    public static PhotoCompleteRequest convertToPhotoCompleteRequest(PhotoGateway photoGateway)
    {
        return PhotoCompleteRequest
                .builder()
                .title(photoGateway.getTitle())
                .url(photoGateway.getUrl())
                .thumbnailUrl(photoGateway.getThumbnailUrl())
                .build();
    }

    public static AlbumCompleteRequest convertToAlbumCompleteRequest(AlbumResponseGateway albumResponseGateway)
    {
        return AlbumCompleteRequest
                .builder()
                .title(albumResponseGateway.getTitle())
                .photos(MapperRequest.mapPhotoCompleteRequestList(albumResponseGateway.getPhotoGateways()))
                .build();
    }

    public static CommentCompleteRequest convertToCommentCompleteRequest(CommentGateway commentGateway)
    {
        return CommentCompleteRequest
                .builder()
                .name(commentGateway.getName())
                .body(commentGateway.getBody())
                .email(commentGateway.getEmail())
                .build();
    }

    public static PostCompleteRequest convertToPostCompleteRequest(PostResponseGateway postResponseGateway)
    {
        return PostCompleteRequest
                .builder()
                .body(postResponseGateway.getBody())
                .title(postResponseGateway.getTitle())
                .comments(MapperRequest.mapCommentCompleteRequestList(postResponseGateway.getCommentGateways()))
                .build();
    }

    public static TodoCompleteRequest convertToTodoCompleteRequest(TodoGateway todoGateway)
    {
        return TodoCompleteRequest
                .builder()
                .completed(todoGateway.getCompleted())
                .title(todoGateway.getTitle())
                .build();
    }

    public static UserCompleteRequest convertToUserCompleteRequest(UserResponseGateway userResponseGateway)
    {
        return UserCompleteRequest
                .builder()
                .email(userResponseGateway.getEmail())
                .name(userResponseGateway.getName())
                .phone(userResponseGateway.getPhone())
                .username(userResponseGateway.getUsername())
                .website(userResponseGateway.getWebsite())

                .company(convertToUserCompanyDTO(userResponseGateway.getCompany()))
                .address(convertToAddressDTO(userResponseGateway.getAddress()))

                .albums(MapperRequest.mapAlbumCompleteRequestList(userResponseGateway.getAlbums()))
                .posts(MapperRequest.mapPostCompleteRequestList(userResponseGateway.getPosts()))
                .todos(MapperRequest.mapTodoCompleteResponseList(userResponseGateway.getTodoGateways()))
                .build();
    }
}
