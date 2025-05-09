
package com.example.CrudOperation.repository;

import com.example.CrudOperation.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity , Integer> {
    Optional<UserEntity> findByUsername(String username);
}

