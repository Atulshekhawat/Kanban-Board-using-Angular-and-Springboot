package com.niit.UserTaskService.service;

import com.niit.UserTaskService.domain.Task;
import com.niit.UserTaskService.domain.User;
import com.niit.UserTaskService.exception.TaskAlreadyExistsException;
import com.niit.UserTaskService.exception.TaskNotFoundException;
import com.niit.UserTaskService.exception.UserAlreadyExistsException;
import com.niit.UserTaskService.exception.UserNotFoundException;
import com.niit.UserTaskService.proxy.UserProxy;
import com.niit.UserTaskService.repository.UserTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ITaskServiceImpl implements ITaskService {

    private UserTaskRepository userTaskRepository;

    private UserProxy userProxy;

    @Autowired
    public ITaskServiceImpl(UserTaskRepository userTaskRepository, UserProxy userProxy) {
        this.userTaskRepository = userTaskRepository;
        this.userProxy = userProxy;
    }

    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        if (userTaskRepository.findById(user.getUserEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        User savedUser = userTaskRepository.save(user);
        if (!(savedUser.getUserEmail().isEmpty())) {
            ResponseEntity<?> savedData = userProxy.saveUser(user);
            System.out.println(savedData.getBody());
        }
        return savedUser;
    }

    @Override
    public User updateUser(String userEmail,User user) {
        Optional<User> optTask = userTaskRepository.findById(userEmail);
        if (optTask.isEmpty())
        {
            return null;
        }
        User existingUser = optTask.get();
        if(user.getUserName()!=null){
            existingUser.setUserName(user.getUserName());
        }
        if(user.getPassword()!=null){
            existingUser.setPassword(user.getPassword());
        }
        return userTaskRepository.save(existingUser);
    }

    @Override
    public List<User> getAllUserEmailAndRoles(String userEmail) throws Exception {
        // Get all the tasks for a specific user
//        if (userTaskRepository.findById(userEmail).isEmpty()) {
//            throw new UserNotFoundException();
//        }
//        return userTaskRepository.findAll();
        if (userTaskRepository.findById(userEmail).isEmpty()) {
            throw new UserNotFoundException();
        }

        // Retrieve all users
        List<User> allUsers = userTaskRepository.findAll();

        // Create a new list to store users with only email populated
        List<User> usersWithEmailsOnly = new ArrayList<>();

        // Populate the list with users containing only emails
        for (User user : allUsers) {
            User userWithEmailOnly = new User();
            userWithEmailOnly.setUserEmail(user.getUserEmail());
            userWithEmailOnly.setRole(user.getRole());
            usersWithEmailsOnly.add(userWithEmailOnly);
        }

        return usersWithEmailsOnly;
    }


    @Override
    public User saveTaskToTaskList(Task task, String userEmail) throws TaskAlreadyExistsException, UserNotFoundException {
        // Save the task to task list of user.
        if (userTaskRepository.findById(userEmail).isEmpty()) {
            throw new UserNotFoundException();
        }

        User user = userTaskRepository.findById(userEmail).orElseThrow(UserNotFoundException::new);
        if (user.getTaskslist() != null && user.getTaskslist().stream().anyMatch(t -> t.getTaskId() == task.getTaskId())) {
            throw new TaskAlreadyExistsException();
        }
        if (user.getTaskslist() == null) {
            user.setTaskslist(Arrays.asList(task));
        } else {
            List<Task> tasks = user.getTaskslist();
            tasks.add(task);
            user.setTaskslist(tasks);
        }
        return userTaskRepository.save(user);
    }

    @Override
    public User updateUserTaskInTaskList(String userEmail, Task task) throws UserNotFoundException, TaskNotFoundException, TaskAlreadyExistsException {
        // Update the specific track details
        boolean flag = false;
        if (userTaskRepository.findById(userEmail).isEmpty()) {
            throw new UserNotFoundException();
        }

        User user = userTaskRepository.findById(userEmail).get();
        List<Task> taskList = user.getTaskslist();

        Iterator<Task> taskIterator = taskList.iterator();
        while (taskIterator.hasNext()) {
            Task task1 = taskIterator.next();
            if (task1.getTaskId().equals(task.getTaskId())) {
                if (task.getTaskName() != null)
                    task1.setTaskName(task.getTaskName());
                if (task.getTaskDescription() != null)
                    task1.setTaskDescription(task.getTaskDescription());
                if (task.getAssignedTo() != null)
                    task1.setAssignedTo(task.getAssignedTo());
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
//            System.out.println("This is the issue");
        }
        user.setTaskslist(taskList);
        return userTaskRepository.save(user);
    }

    @Override
    public User deleteTask(String userId, UUID trackId) throws TaskNotFoundException, UserNotFoundException {
        // delete the user details specified
        boolean userIdIsPresent = false;
        if(userTaskRepository.findById(userId).isEmpty()) {
            throw new UserNotFoundException();
        }
        User user = userTaskRepository.findById(userId).get();
        List<Task> tracks = user.getTaskslist();
        userIdIsPresent = tracks.removeIf(x -> x.getTaskId().equals(trackId) );
        if(!userIdIsPresent) {
            throw  new TaskNotFoundException();
        }
        user.setTaskslist(tracks);
        return userTaskRepository.save(user);
    }

    @Override
    public List<Task> getAllUserTasksFromTaskList(String userEmail) throws Exception {
        // Get all the tracks for a specific user
        if (userTaskRepository.findById(userEmail).isEmpty())
        {
            throw new UserNotFoundException();
        }
        return userTaskRepository.findById(userEmail).get().getTaskslist();
    }

    @Override
    public User retrieveSingleTask(String userEmail, UUID taskId) throws UserNotFoundException,TaskNotFoundException {
        if (userTaskRepository.findById(userEmail).isEmpty()) {
            throw new UserNotFoundException();
        }


        User user = userTaskRepository.findById(userEmail).get();
        List<Task> taskList = user.getTaskslist();

        // Search for the task with the specified taskId
        Optional<Task> optionalTask = taskList.stream()
                .filter(task -> task.getTaskId().equals(taskId))
                .findFirst();

        if (optionalTask.isEmpty()) {
            throw new TaskNotFoundException();
        }

        // Assuming that your User class has a method to set the filtered TaskList
        user.setTaskslist(Collections.singletonList(optionalTask.get()));
        return user;
    }

    @Override
    public String getUserName(String userEmail) throws UserNotFoundException {
        if (userTaskRepository.findById(userEmail).isEmpty()) {
            throw new UserNotFoundException();
        }
        return userTaskRepository.findById(userEmail).get().getUserName();
    }
    @Override
    public String getUserRole(String userEmail) throws UserNotFoundException {
        if (userTaskRepository.findById(userEmail).isEmpty()) {
            throw new UserNotFoundException();
        }
        return userTaskRepository.findById(userEmail).get().getRole();
    }

    @Override
    public List<User> getAllUsers(String userEmail) throws Exception {
        // Get all the tasks for a specific user
        if (userTaskRepository.findById(userEmail).isEmpty()) {
            throw new UserNotFoundException();
        }
        return userTaskRepository.findAll();
    }
}