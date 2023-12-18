package com.niit.UserRegisterService.service;

import com.niit.UserRegisterService.domain.User;
import com.niit.UserRegisterService.exception.InvalidCredentialsException;
import com.niit.UserRegisterService.exception.UserAlreadyExistsException;

public interface UserService {
    public User registerUser(User user) throws UserAlreadyExistsException;

    public void deleteUser(User user) throws InvalidCredentialsException;
}
