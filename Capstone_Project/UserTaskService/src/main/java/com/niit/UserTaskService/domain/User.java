package com.niit.UserTaskService.domain;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class User {
        @Id
        private String userEmail;
        private String userName;
        private String password;
        private String role;

        private List<Task> taskslist;

    public User() {
    }


    public User(String userEmail, String userName, String password, String role, List<Task> taskslist) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.password = password;
        this.role = role;

        this.taskslist = taskslist;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", taskslist=" + taskslist +
                '}';
    }
}

