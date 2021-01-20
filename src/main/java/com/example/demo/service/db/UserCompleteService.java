package com.example.demo.service.db;

import com.example.demo.model.domain.complete.UserCompleteDomain;
import com.example.demo.model.gateway.response.UserResponseGateway;
import com.example.demo.model.request.complete.UserCompleteRequest;
import com.example.demo.model.response.complete.TodoCompleteResponse;
import com.example.demo.model.response.complete.UserCompleteResponse;
import com.example.demo.repository.GatewayRepository;
import com.example.demo.repository.UserCompleteRepository;
import com.example.demo.service.GatewayService;
import com.example.demo.utils.db.ConverterUserDB;
import com.example.demo.utils.db.MapperDB;
import com.example.demo.utils.request.MapperRequest;
import com.example.demo.utils.response.ConverterResponse;
import com.example.demo.utils.response.MapperResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class UserCompleteService {
    private final UserCompleteRepository userRepository;
    private final GatewayService gatewayService;

    public List<UserCompleteResponse> getAll() {
        List<UserCompleteDomain> users = userRepository.findAll();
        return MapperResponse.mapToUserCompleteResponseList(users);
    }

    public List<UserCompleteDomain> getAllDomains() {
        return userRepository.findAll();
    }

    public List<UserCompleteResponse> createAll (List<UserCompleteRequest> request){
        List<UserCompleteDomain> users = MapperDB.mapUserCompleteDomainList(request);
        return MapperResponse.mapToUserCompleteResponseList(userRepository.insert(users));
    }

    public UserCompleteResponse create (UserCompleteRequest request){
        UserCompleteDomain userResponse = userRepository.insert(ConverterUserDB.convertToUserCompleteDomain(request));
        return ConverterResponse.convertUserCompleteDomainToCompleteResponse(userResponse);
    }

    public UserCompleteResponse update (UserCompleteRequest request, String id){
        getById(id);
        UserCompleteDomain user = userRepository.insert(ConverterUserDB.convertToUserCompleteDomain(request));
        user.setId(id);
        user = userRepository.save(user);
        return ConverterResponse.convertUserCompleteDomainToCompleteResponse(user);
    }

    public void delete (String id){
        getById(id);
        userRepository.deleteById(id);
    }

    public List<UserCompleteResponse> refreshCompleteUser() {
        userRepository.deleteAll();
        List<UserResponseGateway> users = gatewayService.getCompleteUser();
        List<UserCompleteRequest> requests = MapperRequest.mapUserCompleteRequestList(users);
        return createAll(requests);
    }




    public UserCompleteResponse getById (String id){
        UserCompleteDomain user = userRepository.findById(id).orElseThrow();
        return ConverterResponse.convertUserCompleteDomainToCompleteResponse(user);
    }

    public List<UserCompleteResponse> getByUsername (String username){
        List<UserCompleteDomain> user = userRepository.findByUsername(username);
        return MapperResponse.mapToUserCompleteResponseList(user);
    }

    public List<UserCompleteResponse> getByEmail (String email){
        List<UserCompleteDomain> user = userRepository.findByEmail(email);
        return MapperResponse.mapToUserCompleteResponseList(user);
    }

    public List<UserCompleteResponse> getByEmailAndUsername (String email, String username){
        List<UserCompleteDomain> user = userRepository.findByEmailAndUsername(email, username);
        return MapperResponse.mapToUserCompleteResponseList(user);
    }

    public List<UserCompleteResponse> getByEmailOrUsername (String email, String username){
        List<UserCompleteDomain> user = userRepository.findByEmailOrUsername(email, username);
        return MapperResponse.mapToUserCompleteResponseList(user);
    }

    public List<UserCompleteResponse> getByAddressStreet (String street){
        List<UserCompleteDomain> user = userRepository.findByAddressStreet(street);
        return MapperResponse.mapToUserCompleteResponseList(user);
    }

    public List<UserCompleteResponse> getByTodosCompleted (Boolean completed){
        List<UserCompleteDomain> user = userRepository.findByTodosCompleted(completed);
        return MapperResponse.mapToUserCompleteResponseList(user);
    }

    public List<TodoCompleteResponse> getByTodosByUserId (String id){
        UserCompleteDomain user = userRepository.findById(id).orElseThrow();
        return MapperResponse.mapToTodoCompleteResponseList(user.getTodos());
    }

    public List<TodoCompleteResponse> getByReversedTodosByUserId (String id){
        UserCompleteDomain user = userRepository.findById(id).orElseThrow();
        user.getTodos().forEach(todo -> {
                todo.setCompleted(!todo.getCompleted());
                todo.setTitle(
                        new StringBuilder()
                        .append(todo.getTitle())
                        .reverse().toString()
                );
        });
        return MapperResponse.mapToTodoCompleteResponseList(user.getTodos());
    }
}
