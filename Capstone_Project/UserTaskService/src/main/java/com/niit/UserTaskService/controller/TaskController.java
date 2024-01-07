package com.niit.UserTaskService.controller;

import com.niit.UserTaskService.domain.Task;
import com.niit.UserTaskService.domain.User;
import com.niit.UserTaskService.exception.TaskAlreadyExistsException;
import com.niit.UserTaskService.exception.TaskNotFoundException;
import com.niit.UserTaskService.exception.UserAlreadyExistsException;
import com.niit.UserTaskService.exception.UserNotFoundException;
import com.niit.UserTaskService.service.ITaskService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")

public class TaskController {

    private ITaskService iTaskService;
    private ResponseEntity responseEntity;

    @Autowired
    public TaskController(ITaskService ITaskService) {
        this.iTaskService = ITaskService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyExistsException {
        try {
            responseEntity = new ResponseEntity<>(iTaskService.registerUser(user), HttpStatus.CREATED);
        } catch (UserAlreadyExistsException ex) {
            throw new UserAlreadyExistsException();
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("user/updateUser/{userEmail}")
    public ResponseEntity<?> update(@RequestBody User user,HttpServletRequest request){
        try{
            System.out.println("header" + request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("id from claims :: " + claims.getSubject());
            String id = claims.getSubject();
            System.out.println("id :: " + id);
            responseEntity = new ResponseEntity<>(iTaskService.updateUser(id,user),HttpStatus.OK);
        }catch (Exception e) {
          responseEntity = new ResponseEntity<>("Could not update!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }

    @PostMapping("user/saveTask")
    public ResponseEntity<?> saveTask(@RequestBody Task task, HttpServletRequest request) throws TaskAlreadyExistsException, UserNotFoundException {
        // add a task to a specific user, return 201 status if task is saved else 500 status
        try {
            System.out.println("header" + request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("id from claims :: " + claims.getSubject());
            String id = claims.getSubject();
            System.out.println("id :: " + id);
            responseEntity = new ResponseEntity<>(iTaskService.saveTaskToTaskList(task, id), HttpStatus.CREATED);
        }catch (TaskAlreadyExistsException ex){
            throw new TaskAlreadyExistsException();
        }
        catch (Exception e) {
            responseEntity = new ResponseEntity<>("Try after some time!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("user/updateTask")
    public ResponseEntity<?> updateTask(@RequestBody Task task, HttpServletRequest request) {
        // update a task based on user id and track id, extract user id from claims
        // return 200 status if updated else return 500 status

        try {
            System.out.println("header" + request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("id from claims :: " + claims.getSubject());
//            String id = claims.getSubject();
            String id = "atulshekhawat21@gmail.com";
//            System.out.println("id :: " + id);
            responseEntity = new ResponseEntity<>(iTaskService.updateUserTaskInTaskList(id, task), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Could not update!!", HttpStatus.INTERNAL_SERVER_ERROR);
//            System.out.println(e);
        }
        return responseEntity;
    }

    @DeleteMapping("user/deleteTask/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable UUID taskId, HttpServletRequest request){
        // delete a task based on user id and track id, extract user id from claims
        // return 200 status if user is saved else 500 status
        Claims claims = (Claims) request.getAttribute("claims");
        System.out.println("id from claims :: " + claims.getSubject());
        String id = claims.getSubject();
        System.out.println("id :: "+id);
        try {
            responseEntity = new ResponseEntity<>(iTaskService.deleteTask(id,taskId), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Could not delete!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return  responseEntity;
    }

    @GetMapping("user/tasks")
    public ResponseEntity<?> getAllTasks(HttpServletRequest request) {
        // display all the tracks of a specific user, extract user id from claims,
        // return 200 status if user is saved else 500 status
        try {

            System.out.println("header" + request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("id from claims :: " + claims.getSubject());
            String id = claims.getSubject();
            System.out.println("id :: "+id);
            responseEntity = new ResponseEntity<>(iTaskService.getAllUserTasksFromTaskList(id), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Failed to fetch Tasks!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @GetMapping("user/users")
    public ResponseEntity<?> getAllUserEmailAndRole(HttpServletRequest request) {
        // display all the tracks of a specific user, extract user id from claims,
        // return 200 status if user is saved else 500 status
        try {
            System.out.println("header" + request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("id from claims :: " + claims.getSubject());
            String id = claims.getSubject();
            System.out.println("id :: "+id);
            responseEntity = new ResponseEntity<>(iTaskService.getAllUserEmailAndRoles(id), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Failed to fetch Users!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/user/get-task/{taskId}")
    public ResponseEntity<?> getATask(@PathVariable UUID taskId, HttpServletRequest request) throws UserNotFoundException {
        try {
            System.out.println("header" + request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("id from claims :: " + claims.getSubject());
            String id = claims.getSubject();
            System.out.println("id :: "+id);
            responseEntity = new ResponseEntity<>(iTaskService.retrieveSingleTask(id, taskId), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Cannot get a task", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/user/getUsername")
    public ResponseEntity<?> getUsername(HttpServletRequest request) throws UserNotFoundException {
        try {
            System.out.println("header" + request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("id from claims :: " + claims.getSubject());
            String id = claims.getSubject();
            System.out.println("id :: "+id);
            responseEntity = new ResponseEntity<>(iTaskService.getUserName(id), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Cannot get Username", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/user/getUserRole")
    public ResponseEntity<?> getUserRole(HttpServletRequest request) throws UserNotFoundException {
        try {
            System.out.println("header" + request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("id from claims :: " + claims.getSubject());
            String id = claims.getSubject();
            System.out.println("id :: "+id);
            responseEntity = new ResponseEntity<>(iTaskService.getUserRole(id), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Cannot get UserRole", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/user/getAllUsers")
    public ResponseEntity<?> getAllUsers(HttpServletRequest request) {
        try {
            System.out.println("header" + request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("id from claims :: " + claims.getSubject());
            String id = claims.getSubject();
            System.out.println("id :: "+id);
            responseEntity = new ResponseEntity<>(iTaskService.getAllUsers(id), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Failed to fetch Users!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}