package com.niit.UserTaskService.service;

import com.niit.UserTaskService.domain.Task;
import com.niit.UserTaskService.domain.User;
import com.niit.UserTaskService.exception.TaskAlreadyExistsException;
import com.niit.UserTaskService.exception.UserAlreadyExistsException;
import com.niit.UserTaskService.exception.UserNotFoundException;
import com.niit.UserTaskService.proxy.UserProxy;
import com.niit.UserTaskService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private UserProxy userProxy;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserProxy userProxy) {
        this.userRepository = userRepository;
        this.userProxy = userProxy;
    }

    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        if (userRepository.findById(user.getUserId()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        User savedUser = userRepository.save(user);
        if (!(savedUser.getUserEmail().isEmpty())){
            ResponseEntity<?> savedData =  userProxy.saveUser(user);
            System.out.println(savedData.getBody());
        }
        return savedUser ;
    }

    @Override
    public User saveTaskToTaskList(Task task, String userEmail) throws TaskAlreadyExistsException, UserNotFoundException {
        // Save the task to task list of user.
        if (userRepository.findById(userEmail).isEmpty()){
            throw new UserNotFoundException();
        }
        User user = userRepository.findById(userEmail).orElseThrow(UserNotFoundException::new);
        if (user.getTaskslist() == null){
            user.setTaskslist(Arrays.asList(task));
        }
        else {
            List<Task> tasks = user.getTaskslist();
            tasks.add(task);
            user.setTaskslist(tasks);
        }
        return userRepository.save(user);
    }
}