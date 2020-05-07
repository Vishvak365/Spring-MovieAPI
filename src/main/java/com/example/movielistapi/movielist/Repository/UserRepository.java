package com.example.movielistapi.movielist.Repository;

import com.example.movielistapi.movielist.Collections.UserCollection;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserCollection, String> {
    
}