package com.application.starter.repositories.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.application.starter.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
