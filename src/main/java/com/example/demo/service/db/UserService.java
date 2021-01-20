package com.example.demo.service.db;

import com.example.demo.model.domain.complete.*;
import com.example.demo.model.domain.user.UserDomain;
import com.example.demo.model.gateway.*;
import com.example.demo.model.gateway.user.UserGateway;
import com.example.demo.model.request.UserRequest;
import com.example.demo.model.response.UserResponse;
import com.example.demo.repository.GatewayRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.db.ConverterUserDB;
import com.example.demo.utils.gateway.FilterGateway;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final GatewayRepository gatewayRepository;
    private final AlbumService albumService;
    private final PhotoService photoService;
    private final PostService postService;
    private final CommentService commentService;
    private final TodoService todoService;
    private final UserCompleteService userCompleteService;

    public List<UserResponse> getAll (){
        List<UserDomain> users = userRepository.findAll();
        return users.stream().map(ConverterUserDB::convertUserDomainToResponse)
                .collect(Collectors.toList());
    }

    public UserResponse getById (String id){
        UserDomain user = userRepository.findById(id).orElseThrow();
        return ConverterUserDB.convertUserDomainToResponse(user);
    }

    public UserResponse create (UserRequest request){
        UserDomain user = ConverterUserDB.convertUserRequestToDomain(request);
        user = userRepository.insert(user);
        return ConverterUserDB.convertUserDomainToResponse(user);
    }

    public String create (UserGateway request){
        UserDomain user = ConverterUserDB.convertUserGatewayToDomain(request);
        user = userRepository.insert(user);
        return user.getId().toHexString();
    }

    public UserResponse update (UserRequest request, String id){
        getById(id);
        UserDomain user = ConverterUserDB.convertUserRequestToDomain(request);
        user.setId(new ObjectId(id));
        user = userRepository.save(user);
        return ConverterUserDB.convertUserDomainToResponse(user);
    }

    public void delete (String id){
        getById(id);
        userRepository.deleteById(id);
    }

    public List<UserResponse> getUserByGeoRanges(Double minLat, Double maxLat, Double minLong, Double maxLong)
    {
        return userRepository.getUserByGeoRanges(minLat, maxLat, minLong, maxLong)
                .stream()
                .map(ConverterUserDB::convertUserDomainToResponse)
                .collect(Collectors.toList());
    }

    public List<UserCompleteDomain> getCompleteUsers()
    {
        return userRepository.getCompleteUsers();
    }

    public void usersDBValidate() throws JsonProcessingException {
        List<UserCompleteDomain> userCompleteDomainsFromDomains = getCompleteUsers();
        List<UserCompleteDomain> userCompleteDomains = userCompleteService.getAllDomains();


        if(userCompleteDomains.size() != userCompleteDomainsFromDomains.size())
            throw new RuntimeException("Does not match.");

        userCompleteDomains = sortUserCompleteDomains(userCompleteDomains);
        userCompleteDomainsFromDomains = sortUserCompleteDomains(userCompleteDomainsFromDomains);

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        System.out.println(objectWriter.writeValueAsString(userCompleteDomains));
        System.out.println(objectWriter.writeValueAsString(userCompleteDomainsFromDomains));

        if(!userCompleteDomains.equals(userCompleteDomainsFromDomains))
            throw new RuntimeException("Does not match.");
    }

    private List<UserCompleteDomain> sortUserCompleteDomains(List<UserCompleteDomain> userCompleteDomains)
    {
        userCompleteDomains = userCompleteDomains.stream().sorted(Comparator.comparing(UserCompleteDomain::getName)).collect(Collectors.toList());

        userCompleteDomains.forEach(user -> {
            user.setTodos(user.getTodos().stream().sorted(Comparator.comparing(TodoCompleteDomain::getTitle)).collect(Collectors.toList()));
            user.setAlbums(user
                    .getAlbums()
                    .stream()
                    .sorted(Comparator.comparing(AlbumCompleteDomain::getTitle)).collect(Collectors.toList())
            );

            user.getAlbums().forEach(album -> {
                album.setPhotos(album.getPhotos().stream().sorted(Comparator.comparing(PhotoCompleteDomain::getTitle)).collect(Collectors.toList()));
            });

            user.setPosts(user
                    .getPosts()
                    .stream()
                    .sorted(Comparator.comparing(PostCompleteDomain::getTitle)).collect(Collectors.toList())
            );

            user.getPosts().forEach(post -> {
                post.setComments(post.getComments().stream().sorted(Comparator.comparing(CommentCompleteDomain::getName)).collect(Collectors.toList()));
            });
        });
        return userCompleteDomains;
    }

    public void purge()
    {
        userRepository.deleteAll();
    }

    public void fullPurge()
    {
        purge();
        albumService.purge();
        photoService.purge();
        postService.purge();
        commentService.purge();
        todoService.purge();
    }

    public void seedUser()
    {
        fullPurge();
        List<UserGateway> userGateways = gatewayRepository.getUsers();
        if(!Objects.isNull(userGateways))
        {
            List<PostGateway> postGateways = gatewayRepository.getPosts();
            List<CommentGateway> commentGateways = gatewayRepository.getComments();
            List<AlbumGateway> albumGateways = gatewayRepository.getAlbums();
            List<PhotoGateway> photoGateways = gatewayRepository.getPhotos();
            List<TodoGateway> todoGateways = gatewayRepository.getTodos();

            userGateways.forEach(userGateway ->
            {
                String userId = create(userGateway);
                List<AlbumGateway> albumGatewaysFiltered = FilterGateway.filteredAlbumGatewayByUserId(albumGateways, userGateway.getId());
                List<PostGateway> postGatewaysFiltered = FilterGateway.filteredPostGatewayByUserId(postGateways, userGateway.getId());
                List<TodoGateway> todoGatewaysFiltered = FilterGateway.filteredTodoByUserId(todoGateways, userGateway.getId());

                albumService.seed(albumGatewaysFiltered, userId, photoGateways);
                postService.seed(postGatewaysFiltered, userId, commentGateways);
                todoService.seed(todoGatewaysFiltered, userId);
            });
        }
    }
}
