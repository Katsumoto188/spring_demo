package com.example.demo.controller.db;

import com.example.demo.model.request.TodoRequest;
import com.example.demo.model.request.complete.UserCompleteRequest;
import com.example.demo.model.response.TodoResponse;
import com.example.demo.model.response.complete.TodoCompleteResponse;
import com.example.demo.model.response.complete.UserCompleteResponse;
import com.example.demo.service.db.UserCompleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/db/complete")
public class UserCompleteController {
    private final UserCompleteService userCompleteService;

    @PutMapping("/seed")
    public List<UserCompleteResponse> seedCompleteUserResponse(){
        return userCompleteService.refreshCompleteUser();
    }

    @GetMapping("/")
    public List<UserCompleteResponse> getAll(){
        return userCompleteService.getAll();
    }

    @PostMapping
    public UserCompleteResponse create (@Valid @RequestBody UserCompleteRequest request){
        return userCompleteService.create(request);
    }

    @PutMapping ("/{id}")
    public UserCompleteResponse update (@RequestBody UserCompleteRequest request, @PathVariable String id){
        return userCompleteService.update(request, id);
    }

    @DeleteMapping("{id}")
    public void delete (@PathVariable String id){
        userCompleteService.delete(id);
    }





    @GetMapping ("/{id}")
    public UserCompleteResponse getById (@PathVariable String id){
        return userCompleteService.getById(id);
    }

    @GetMapping ("/todoByUserid/{id}")
    public List<TodoCompleteResponse> getByTodosByUserId (@PathVariable String id){
        return userCompleteService.getByTodosByUserId(id);
    }

    @GetMapping ("/reversedTodoByUserid/{id}")
    public List<TodoCompleteResponse> getByReversedTodosByUserId (@PathVariable String id){
        return userCompleteService.getByReversedTodosByUserId(id);
    }

    @GetMapping ("/findByUsername/{username}")
    public List<UserCompleteResponse> getByUsername (@PathVariable String username){
        return userCompleteService.getByUsername(username);
    }

    @GetMapping ("/findByEmail")
    public List<UserCompleteResponse> getByEmail (@RequestParam String email){
        return userCompleteService.getByEmail(email);
    }

    @GetMapping ("/findByEmailAndUsername")
    public List<UserCompleteResponse> getByEmailAndUsername (@RequestParam String email, @RequestParam String username){
        return userCompleteService.getByEmailAndUsername(email, username);
    }

    @GetMapping ("/findByEmailOrUsername")
    public List<UserCompleteResponse> getByEmailOrUsername (@RequestParam(required = false) String email, @RequestParam(required = false) String username){
        return userCompleteService.getByEmailOrUsername(email, username);
    }

    @GetMapping ("/findByAddressStreet")
    public List<UserCompleteResponse> getByAddressStreet (@RequestParam String street){
        return userCompleteService.getByAddressStreet(street);
    }

    @GetMapping ("/findByTodosCompleted")
    public List<UserCompleteResponse> getByTodosCompleted (@RequestParam Boolean completed){
        return userCompleteService.getByTodosCompleted(completed);
    }
}
