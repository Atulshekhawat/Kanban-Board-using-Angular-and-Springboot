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
    public User retrieveSingleTodo(String userEmail, UUID taskId) throws UserNotFoundException,TaskNotFoundException {
        if (userTaskRepository.findById(userEmail).isEmpty()) {
            throw new UserNotFoundException();
        }


        User user = userTaskRepository.findById(userEmail).get();
        List<Task> todoList = user.getTaskslist();

        // Search for the todo with the specified todoID
        Optional<Task> optionalTodo = todoList.stream()
                .filter(todo -> todo.getTaskId().equals(taskId))
                .findFirst();

        if (optionalTodo.isEmpty()) {
            throw new TaskNotFoundException();
        }

        // Assuming that your User class has a method to set the filtered TodoList
        user.setTaskslist(Collections.singletonList(optionalTodo.get()));
        return user;
    }
}