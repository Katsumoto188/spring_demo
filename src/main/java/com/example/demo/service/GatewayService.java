package com.example.demo.service;

import com.example.demo.model.domain.complete.AlbumCompleteDomain;
import com.example.demo.model.domain.complete.PostCompleteDomain;
import com.example.demo.model.domain.complete.TodoCompleteDomain;
import com.example.demo.model.domain.complete.UserCompleteDomain;
import com.example.demo.model.domain.user.UserDomain;
import com.example.demo.model.gateway.*;
import com.example.demo.model.gateway.response.AlbumResponseGateway;
import com.example.demo.model.gateway.response.PostResponseGateway;
import com.example.demo.model.gateway.response.UserResponseGateway;
import com.example.demo.model.gateway.user.UserGateway;
import com.example.demo.model.request.complete.UserCompleteRequest;
import com.example.demo.model.response.UserResponse;
import com.example.demo.model.response.complete.UserCompleteResponse;
import com.example.demo.repository.GatewayRepository;
import com.example.demo.service.db.UserCompleteService;
import com.example.demo.service.db.UserService;
import com.example.demo.utils.db.ConverterUserDB;
import com.example.demo.utils.gateway.MapperGateway;
import com.example.demo.utils.request.MapperRequest;
import com.example.demo.utils.response.MapperResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class GatewayService {

    private final GatewayRepository gatewayRepository;

    public List<PostResponseGateway> getCompletePost (){
        List<PostGateway> postGateways = gatewayRepository.getPosts();
        List<CommentGateway> commentGateways = gatewayRepository.getComments();
        List<PostResponseGateway> responsePost = MapperGateway.mapToPostResponseList(postGateways, commentGateways);
        return responsePost;
    }

    public List<AlbumResponseGateway> getCompleteAlbum(){
        List<AlbumGateway> albumGateways = gatewayRepository.getAlbums();
        List<PhotoGateway> photoGateways = gatewayRepository.getPhotos();
        List<AlbumResponseGateway> responseAlbum = MapperGateway.mapToAlbumResponseList(albumGateways, photoGateways);
        return responseAlbum;
    };

    public List<UserResponseGateway> getCompleteUser(){
        List<UserGateway> userGateways = gatewayRepository.getUsers();
        List<TodoGateway> todoGateways = gatewayRepository.getTodos();
        List<AlbumResponseGateway> albums = getCompleteAlbum();
        List<PostResponseGateway> posts = getCompletePost();
        List<UserResponseGateway> responseUser = MapperGateway.mapToUserResponseList(userGateways, todoGateways, albums, posts);
        return responseUser;
    };

    public UserResponseGateway getSpecificUser (Integer id){
        UserGateway userGateway = gatewayRepository.getUserById(id).stream().findFirst().orElse(null);
        if(Objects.isNull(userGateway))
            return null;

        List<PostGateway> postGateways = gatewayRepository.getPostByUserId(id);
        List<CommentGateway> commentGateways = gatewayRepository.getComments();
        List<AlbumGateway> albumGateways = gatewayRepository.getAlbumByUserId(id);
        List<PhotoGateway> photoGateways = gatewayRepository.getPhotos();
        List<TodoGateway> todoGateways = gatewayRepository.getTodoByUserId(id);

        List<AlbumResponseGateway> responseAlbum = MapperGateway.mapToAlbumResponseList(albumGateways, photoGateways);

        List<PostResponseGateway> responsePost = MapperGateway.mapToPostResponseList(postGateways, commentGateways);

        return MapperGateway.mapToUserResponse(userGateway, todoGateways, responseAlbum, responsePost);
//        return jsonPlaceHolderRepository.getSpecificUser(id).stream().findFirst().orElse(null);
    }
}
