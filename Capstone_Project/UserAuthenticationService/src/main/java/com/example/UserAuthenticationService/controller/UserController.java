package com.example.UserAuthenticationService.controller;


import com.example.UserAuthenticationService.domain.User;
import com.example.UserAuthenticationService.exception.InvalidCredentialsException;
import com.example.UserAuthenticationService.exception.UserAlreadyExistsException;
import com.example.UserAuthenticationService.security.SecurityTokenGenerator;
import com.example.UserAuthenticationService.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/v2")
public class UserController {

    //Autowire the dependencies for UserService and SecurityTokenGenerator
    private IUserService userService;
    private SecurityTokenGenerator securityTokenGenerator;
    private ResponseEntity responseEntity;

    @Autowired
    public UserController(IUserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }


    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyExistsException {
        // Write the logic to save a user,
        // return 201 status if user is saved else 500 status
        try {
            responseEntity = new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);

        } catch (UserAlreadyExistsException e) {
            throw new UserAlreadyExistsException();
        }

        return responseEntity;

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) throws InvalidCredentialsException {
        // Generate the token on login,
        // return 200 status if user is saved else 500 status
        User retrivedUser = userService.getUserByUserIdAndPassword(user.getUserEmail(), user.getPassword());
        if (retrivedUser == null) {
            throw new InvalidCredentialsException();
        }

        String token = securityTokenGenerator.createToken(user);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
