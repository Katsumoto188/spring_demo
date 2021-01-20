package com.example.demo.controller.db;

import com.example.demo.model.domain.complete.UserCompleteDomain;
import com.example.demo.model.request.UserRequest;
import com.example.demo.model.response.UserResponse;
import com.example.demo.service.db.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/db/user")

public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserResponse> getAll (){

        return userService.getAll();
    }

    @GetMapping ("/{id}")
    public UserResponse getById (@PathVariable String id){
        return userService.getById(id);
    }

    @PostMapping
    public UserResponse create (@RequestBody UserRequest user){
        return userService.create(user);
    }

    @PutMapping ("/{id}")
    public UserResponse update (@RequestBody UserRequest user,
                                @PathVariable String id){
        return userService.update(user, id);
    }

    @DeleteMapping ("/{id}")
    public void delete (@PathVariable String id){
        userService.delete(id);
    }

    @PutMapping("/seed")
    public void seed()
    {
        userService.seedUser();
    }

    @GetMapping("/getUserByGeoRanges")
    public List<UserResponse> getUserByGeoRanges(@RequestParam Double minLat, @RequestParam Double maxLat, @RequestParam Double minLong, @RequestParam Double maxLong)
    {
        return userService.getUserByGeoRanges(minLat, maxLat, minLong, maxLong);
    }

    @GetMapping("/getUsers")
    public List<UserCompleteDomain> getCompleteUsers() { return userService.getCompleteUsers(); }

    @GetMapping("/usersDBValidade")
    public void userDBValidate() throws JsonProcessingException { userService.usersDBValidate(); }
}
