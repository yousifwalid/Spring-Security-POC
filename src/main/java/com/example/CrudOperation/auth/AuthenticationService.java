package com.example.CrudOperation.auth;

import com.example.CrudOperation.filter.JwtService;
import com.example.CrudOperation.model.UserEntity;
import com.example.CrudOperation.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String createUser(UserEntity user) {
        UserEntity user1 = UserEntity.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(user.getRole())
                .build();
        UserEntity result = userRepo.save(user1);
        String jwtToken = jwtService.generateToken(result);
        return jwtToken;
    }
    public UserEntity addUser(UserEntity user){
        return userRepo.save(user);
    }
}