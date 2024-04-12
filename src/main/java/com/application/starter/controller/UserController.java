package com.application.starter.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.starter.model.User;
import com.application.starter.repositories.mongo.UserRepository;
import com.application.starter.service.ChangeStreamService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final ChangeStreamService changeStreamService;

    @GetMapping("/hello")
    public String hello() {
        try {
            changeStreamService.watchCollection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Hello World!";
    }

    //add user
    @PostMapping()
    public User addUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    //get all users
    @GetMapping()
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    //get user by id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return userRepository.findById(id).get();
    }

    //delete user by id
    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable String id) {
        userRepository.deleteById(id);
        return "User deleted with id: " + id;
    }

}
