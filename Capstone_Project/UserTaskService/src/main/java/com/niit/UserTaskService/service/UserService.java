package com.niit.UserTaskService.service;

import com.niit.UserTaskService.domain.Task;
import com.niit.UserTaskService.domain.User;
import com.niit.UserTaskService.exception.TaskAlreadyExistsException;
import com.niit.UserTaskService.exception.TaskNotFoundException;
import com.niit.UserTaskService.exception.UserAlreadyExistsException;
import com.niit.UserTaskService.exception.UserNotFoundException;

public interface UserService {
    public User registerUser(User user) throws UserAlreadyExistsException;
    User saveTaskToTaskList(Task task, String userEmail) throws TaskAlreadyExistsException, UserNotFoundException;

    User updateUserTaskInTaskList(String userEmail,Task task) throws UserNotFoundException, TaskNotFoundException, TaskAlreadyExistsException;

    User deleteTrack(String userId,String trackId) throws TaskNotFoundException, UserNotFoundException;

}