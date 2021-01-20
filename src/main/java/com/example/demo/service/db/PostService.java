package com.example.demo.service.db;

import com.example.demo.model.domain.PostDomain;
import com.example.demo.model.gateway.CommentGateway;
import com.example.demo.model.gateway.PostGateway;
import com.example.demo.model.request.AlbumRequest;
import com.example.demo.model.request.PostRequest;
import com.example.demo.model.response.AlbumResponse;
import com.example.demo.model.response.PostResponse;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.db.ConverterPostDB;
import com.example.demo.utils.gateway.FilterGateway;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentService commentService;

    public List<PostResponse> getAll(){
        List<PostDomain> posts = postRepository.findAll();
        return posts.stream().map(ConverterPostDB::convertPostDomainToResponse)
                .collect(Collectors.toList());
    }

    public PostResponse getById(String id){
        PostDomain post = postRepository.findById(id).orElseThrow();
        return ConverterPostDB.convertPostDomainToResponse(post);
    }

    public PostResponse create (PostRequest request){
        userRepository.findById(request.getUserId()).orElseThrow();
        PostDomain post = ConverterPostDB.convertPostRequestToDomain(request);
        post = postRepository.insert(post);
        return ConverterPostDB.convertPostDomainToResponse(post);
    }

    public String create (PostGateway request, String userId){
        userRepository.findById(userId).orElseThrow();

        PostDomain post = ConverterPostDB.convertPostGatewayToCompleteDomain(request);
        post.setUserId(new ObjectId(userId));
        post = postRepository.insert(post);

        return post.getId().toHexString();
    }

    public PostResponse update (PostRequest request, String id){
        userRepository.findById(request.getUserId()).orElseThrow();
        PostDomain post = ConverterPostDB.convertPostRequestToDomain(request);
        post.setId(new ObjectId(id));
        post = postRepository.save(post);
        return ConverterPostDB.convertPostDomainToResponse(post);
    }

    public void delete (String id){
        getById(id);
        postRepository.deleteById(id);
    }

    public void purge()
    {
        postRepository.deleteAll();
    }

    public void seed(List<PostGateway> postGateways, String userId, List<CommentGateway> commentGateways)
    {
        postGateways.forEach(postGateway ->
        {
            String postId = create(postGateway, userId);

            commentService.seed(
                    FilterGateway.filteredCommentByPostId(commentGateways, postGateway.getId()),
                    postId
            );
        });
    }

}
