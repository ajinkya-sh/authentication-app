package com.ajinkya.authenticationApp.repository;

import com.ajinkya.authenticationApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String username);
}
