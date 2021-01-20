package com.example.demo.model.request.complete;

import com.example.demo.model.dto.user.UserAddressDTO;
import com.example.demo.model.dto.user.UserCompanyDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class UserCompleteRequest {

    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;

    private UserAddressDTO address;
    private UserCompanyDTO company;

    private List<PostCompleteRequest> posts;
    private List<AlbumCompleteRequest> albums;
    private List<TodoCompleteRequest> todos;
}
