package com.niit.UserTaskService.service;

import com.niit.UserTaskService.domain.Task;
import com.niit.UserTaskService.domain.User;
import com.niit.UserTaskService.exception.TaskAlreadyExistsException;
import com.niit.UserTaskService.exception.TaskNotFoundException;
import com.niit.UserTaskService.exception.UserAlreadyExistsException;
import com.niit.UserTaskService.exception.UserNotFoundException;

import java.util.List;

public interface ITaskService {
    public User registerUser(User user) throws UserAlreadyExistsException;

    User updateUser(String userEmail,User user);
    User saveTaskToTaskList(Task task, String userEmail) throws TaskAlreadyExistsException, UserNotFoundException;
    User updateUserTaskInTaskList(String userEmail,Task task) throws UserNotFoundException, TaskNotFoundException, TaskAlreadyExistsException;
    User deleteTask(String userEmail,int taskId) throws TaskNotFoundException, UserNotFoundException;

    List<Task> getAllUserTasksFromTaskList(String userEmail) throws Exception;

}