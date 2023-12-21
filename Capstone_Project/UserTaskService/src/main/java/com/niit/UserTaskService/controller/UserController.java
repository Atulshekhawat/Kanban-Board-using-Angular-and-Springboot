package com.niit.UserTaskService.controller;

import com.niit.UserTaskService.domain.Task;
import com.niit.UserTaskService.domain.User;
import com.niit.UserTaskService.exception.TaskAlreadyExistsException;
import com.niit.UserTaskService.exception.UserAlreadyExistsException;
import com.niit.UserTaskService.exception.UserNotFoundException;
import com.niit.UserTaskService.service.UserService;
import io.jsonwebtoken.Claims;
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

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyExistsException {
        try {
            responseEntity = new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
        } catch (UserAlreadyExistsException ex) {
            throw new UserAlreadyExistsException();
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping("user/task")
    public ResponseEntity<?> saveTask(@RequestBody Task task, HttpServletRequest request) {
        // add a task to a specific user, return 201 status if task is saved else 500 status
        try {
            System.out.println("header" + request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("id from claims :: " + claims.getSubject());
            String id = claims.getSubject();
            System.out.println("id :: " + id);
            responseEntity = new ResponseEntity<>(userService.saveTaskToTaskList(task, id), HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Try after some time!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("user/task")
    public ResponseEntity<?> updateTrack(@RequestBody Task task, HttpServletRequest request) {
        // update a track based on user id and track id, extract user id from claims
        // return 200 status if updated else return 500 status

        try {
            System.out.println("header" + request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("id from claims :: " + claims.getSubject());
            String id = claims.getSubject();
            System.out.println("id :: " + id);
//            String id = getUserIdFromClaims(request);
            responseEntity = new ResponseEntity<>(userService.updateUserTaskInTaskList(id, task), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Could not update!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}