package com.example.demo.repository;

import com.example.demo.model.domain.complete.UserCompleteDomain;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UserCompleteRepository extends MongoRepository<UserCompleteDomain, String> {

    List<UserCompleteDomain> findByUsername(String username);
    List<UserCompleteDomain> findByEmail(String email);
    List<UserCompleteDomain> findByEmailAndUsername(String email, String username);
    List<UserCompleteDomain> findByEmailOrUsername(String email, String username);
    List<UserCompleteDomain> findByAddressStreet(String street);
    List<UserCompleteDomain> findByTodosCompleted(Boolean completed);
}
