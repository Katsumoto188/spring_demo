package com.example.demo.model.response.complete;

import com.example.demo.model.dto.user.UserAddressDTO;
import com.example.demo.model.dto.user.UserCompanyDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class UserCompleteResponse {

    private String id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;

    private UserAddressDTO address;
    private UserCompanyDTO company;

    private List<PostCompleteResponse> posts;
    private List<AlbumCompleteResponse> albums;
    private List<TodoCompleteResponse> todos;
}
