package com.example.demo.service.db;

import com.example.demo.model.domain.CommentDomain;
import com.example.demo.model.domain.PhotoDomain;
import com.example.demo.model.gateway.CommentGateway;
import com.example.demo.model.request.AlbumRequest;
import com.example.demo.model.request.CommentRequest;
import com.example.demo.model.response.AlbumResponse;
import com.example.demo.model.response.CommentResponse;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.utils.db.ConverterCommentDB;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public List<CommentResponse> getAll (){
        List<CommentDomain> comments = commentRepository.findAll();
        return comments.stream().map(ConverterCommentDB::convertCommentDomainToResponse)
                .collect(Collectors.toList());
    }

    public CommentResponse getById (String id){
        CommentDomain comment = commentRepository.findById(id).orElseThrow();
        return ConverterCommentDB.convertCommentDomainToResponse(comment);
    }

    public CommentResponse create (CommentRequest request){
        postRepository.findById(request.getPostId()).orElseThrow();
        CommentDomain comment = ConverterCommentDB.convertCommentRequestToDomain(request);
        comment = commentRepository.insert(comment);
        return ConverterCommentDB.convertCommentDomainToResponse(comment);
    }

    public void create (List<CommentDomain> comments){
        commentRepository.saveAll(comments);
    }

    public CommentResponse update (CommentRequest request, String id){
        postRepository.findById(request.getPostId()).orElseThrow();
        getById(id);
        CommentDomain comment = ConverterCommentDB.convertCommentRequestToDomain(request);
        comment.setId(new ObjectId(id));
        comment = commentRepository.save(comment);
        return ConverterCommentDB.convertCommentDomainToResponse(comment);
    }

    public void delete (String id){
        getById(id);
        commentRepository.deleteById(id);
    }

    public void purge()
    {
        commentRepository.deleteAll();
    }

    public void seed(List<CommentGateway> commentGateways, String postId)
    {
        ArrayList<CommentDomain> commentsToSave = new ArrayList<CommentDomain>();
        commentGateways.forEach(commentGateway ->
        {
            CommentDomain comment = ConverterCommentDB.convertToCommentGateway(commentGateway);
            comment.setPostId(new ObjectId(postId));
            commentsToSave.add(comment);
        });
        create(commentsToSave);
    }
}
