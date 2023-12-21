package com.niit.UserTaskService.service;

import com.niit.UserTaskService.domain.Task;
import com.niit.UserTaskService.domain.User;
import com.niit.UserTaskService.exception.TaskAlreadyExistsException;
import com.niit.UserTaskService.exception.TaskNotFoundException;
import com.niit.UserTaskService.exception.UserAlreadyExistsException;
import com.niit.UserTaskService.exception.UserNotFoundException;
import com.niit.UserTaskService.proxy.UserProxy;
import com.niit.UserTaskService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Iterator;
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
        if (userRepository.findById(user.getUserEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        User savedUser = userRepository.save(user);
        if (!(savedUser.getUserEmail().isEmpty())) {
            ResponseEntity<?> savedData = userProxy.saveUser(user);
            System.out.println(savedData.getBody());
        }
        return savedUser;
    }

    @Override
    public User saveTaskToTaskList(Task task, String userEmail) throws TaskAlreadyExistsException, UserNotFoundException {
        // Save the task to task list of user.
        if (userRepository.findById(userEmail).isEmpty()) {
            throw new UserNotFoundException();
        }
        User user = userRepository.findById(userEmail).orElseThrow(UserNotFoundException::new);
        if (user.getTaskslist() == null) {
            user.setTaskslist(Arrays.asList(task));
        } else {
            List<Task> tasks = user.getTaskslist();
            tasks.add(task);
            user.setTaskslist(tasks);
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUserTaskInTaskList(String userEmail, Task task) throws UserNotFoundException, TaskNotFoundException, TaskAlreadyExistsException {
        // Update the specific track details
        boolean flag = false;
        if (userRepository.findById(userEmail).isEmpty()) {
            throw new UserNotFoundException();
        }

        User user = userRepository.findById(userEmail).get();
        List<Task> taskList = user.getTaskslist();

        Iterator<Task> taskIterator = taskList.iterator();
        while (taskIterator.hasNext()) {
            Task task1 = taskIterator.next();
            if (task1.getTaskId()==(task.getTaskId())) {
                if (task.getTaskName() != null)
                    task1.setTaskName(task.getTaskName());
                if (task.getTaskDescription() != null)
                    task1.setTaskDescription(task.getTaskDescription());
                if (task.getAssignee() != null)
                    task1.setAssignee(task.getAssignee());
                if (task.getDueDate() != null)
                    task1.setDueDate(task.getDueDate());
                if (task.getPriority() != null)
                    task1.setPriority(task.getPriority());
                if (task.getStatus() != null)
                    task1.setStatus(task.getStatus());
                flag = true;
            }
        }
        if (!flag) {
            throw new TaskNotFoundException();
        }
        user.setTaskslist(taskList);
        return userRepository.save(user);
    }

    @Override
    public User deleteTrack(String userId, String trackId) throws TaskNotFoundException, UserNotFoundException {
        // delete the user details specified
        boolean userIdIsPresent = false;
        if(userRepository.findById(userId).isEmpty()) {
            throw new UserNotFoundException();
        }
        User user = userRepository.findById(userId).get();
        List<Task> tasks = user.getTaskslist();
        userIdIsPresent = tasks.removeIf(x -> Integer.toString(x.getTaskId()).equals(trackId));
        if(!userIdIsPresent) {
            throw  new TaskNotFoundException();
        }
        user.setTaskslist(tasks);
        return userRepository.save(user);
    }
}