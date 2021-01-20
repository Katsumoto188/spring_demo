package com.example.demo.model.domain.complete;

import com.example.demo.model.domain.user.UserAddressDomain;
import com.example.demo.model.domain.user.UserCompanyDomain;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document (collection = "user_complete")

public class UserCompleteDomain {

    @Id
    private String id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;

    private UserAddressDomain address;
    private UserCompanyDomain company;

    private List<PostCompleteDomain> posts;
    private List<AlbumCompleteDomain> albums;
    private List<TodoCompleteDomain> todos;
}
