package com.niit.UserTaskService.service;

import com.niit.UserTaskService.domain.Task;
import com.niit.UserTaskService.domain.User;
import com.niit.UserTaskService.exception.TaskAlreadyExistsException;
import com.niit.UserTaskService.exception.TaskNotFoundException;
import com.niit.UserTaskService.exception.UserAlreadyExistsException;
import com.niit.UserTaskService.exception.UserNotFoundException;

import java.util.List;
import java.util.UUID;

public interface ITaskService {
    public User registerUser(User user) throws UserAlreadyExistsException;

    User updateUser(String userEmail,User user);
    User saveTaskToTaskList(Task task, String userEmail) throws TaskAlreadyExistsException, UserNotFoundException;
    User updateUserTaskInTaskList(String userEmail,Task task) throws UserNotFoundException, TaskNotFoundException, TaskAlreadyExistsException;
    User deleteTask(String userEmail, UUID taskId) throws TaskNotFoundException, UserNotFoundException;
    List<Task> getAllUserTasksFromTaskList(String userEmail) throws Exception;
    User retrieveSingleTodo(String userEmail, UUID taskId) throws UserNotFoundException, TaskNotFoundException;
    String getUserName(String userEmail) throws UserNotFoundException;
}