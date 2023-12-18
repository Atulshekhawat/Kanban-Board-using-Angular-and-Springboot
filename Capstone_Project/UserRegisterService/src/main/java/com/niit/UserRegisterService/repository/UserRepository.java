package com.niit.UserRegisterService.repository;

import com.niit.UserRegisterService.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User,String> {
    User findByUserNameAndCreatorEmail(String userEmail,String creatorEmail);
}
