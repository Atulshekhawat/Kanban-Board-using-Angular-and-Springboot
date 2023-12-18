package com.example.UserAuthenticationService.repository;


import com.example.UserAuthenticationService.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUserEmailAndPassword(String userEmail, String password);
}
