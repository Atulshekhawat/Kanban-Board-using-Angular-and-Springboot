package com.niit.UserRegisterService.controller;

import com.niit.UserRegisterService.domain.User;
import com.niit.UserRegisterService.exception.InvalidCredentialsException;
import com.niit.UserRegisterService.exception.UserAlreadyExistsException;
import com.niit.UserRegisterService.service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private UserService userService;
    private ResponseEntity responseEntity;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/save")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyExistsException{
        try {
            responseEntity = new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
        } catch (UserAlreadyExistsException ex) {
            throw new UserAlreadyExistsException();
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
 }
