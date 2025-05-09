package com.example.CrudOperation.controller;

import com.example.CrudOperation.auth.AuthenticationService;
import com.example.CrudOperation.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    private final AuthenticationService authenticationService;

    @PostMapping("/users/create")                        //localhost:8081/auth/users/create       //create new user
    public String createUser(@RequestBody UserEntity user) {
        return authenticationService.createUser(user);
    }
    @PostMapping("/users/add")
    public UserEntity addUser(@RequestBody UserEntity user){
        return authenticationService.addUser(user);
    }
}
