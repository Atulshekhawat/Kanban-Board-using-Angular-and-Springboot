package com.niit.UserRegisterService.controller;

import com.niit.UserRegisterService.domain.User;
import com.niit.UserRegisterService.exception.InvalidCredentialsException;
import com.niit.UserRegisterService.exception.UserAlreadyExistsException;
import com.niit.UserRegisterService.service.UserService;
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

    @DeleteMapping("/user/{userEmail}")
    public ResponseEntity<?> deleteUser(@RequestBody User user) throws InvalidCredentialsException{
        try {
            userService.deleteUser(user);
            responseEntity = new ResponseEntity<>("user deleted", HttpStatus.OK);
        } catch (InvalidCredentialsException ex) {
            throw new InvalidCredentialsException();
        } catch (Exception ex ) {
            responseEntity = new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }
 }
