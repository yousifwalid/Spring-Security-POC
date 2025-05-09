package com.example.CrudOperation.controller;

import com.example.CrudOperation.model.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class registerRequest {
    private int id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
