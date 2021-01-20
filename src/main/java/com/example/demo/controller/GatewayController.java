package com.example.demo.controller;

import com.example.demo.model.gateway.response.AlbumResponseGateway;
import com.example.demo.model.gateway.response.PostResponseGateway;
import com.example.demo.model.gateway.response.UserResponseGateway;
import com.example.demo.service.GatewayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/complete")

public class GatewayController {

    private final GatewayService gatewayService;

    @GetMapping ("/post")
    public List<PostResponseGateway> getCompletePostResponse () {
        return gatewayService.getCompletePost();
    }

    @GetMapping ("/album")
    public List<AlbumResponseGateway> getCompleteAlbumResponse (){
        return gatewayService.getCompleteAlbum();
    }

    @GetMapping ("/user")
    public List<UserResponseGateway> getCompleteUserResponse(){
        return gatewayService.getCompleteUser();
    }

    @GetMapping ("/userById")
    public UserResponseGateway getUserById(@RequestParam Integer id){
        return gatewayService.getSpecificUser(id);
    }
}