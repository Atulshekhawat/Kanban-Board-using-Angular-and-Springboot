package com.niit.UserTaskService.domain;



import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class User {
        @Id
        private String userEmail;
        private String userId;
        private String userName;
        private String password;
        private List<Task> taskslist;

    public User() {
    }


    public User(String userEmail, String userId, String userName, String password, List<Task> taskslist) {
        this.userEmail = userEmail;
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.taskslist = taskslist;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public List<Task> getTaskslist() {
        return taskslist;
    }

    public void setTaskslist(List<Task> taskslist) {
        this.taskslist = taskslist;
    }

    @Override
    public String toString() {
        return "User{" +
                "userEmail='" + userEmail + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", taskslist=" + taskslist +
                '}';
    }
}

