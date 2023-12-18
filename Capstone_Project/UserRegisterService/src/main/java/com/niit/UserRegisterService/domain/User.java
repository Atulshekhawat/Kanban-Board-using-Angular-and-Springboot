package com.niit.UserRegisterService.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;



@Entity
public class User {
    @Id
    private String userEmail;
    private String userId;
    private String userName;
    private String password;
    private String creatorEmail;

    public User() {
    }

    public User(String userEmail, String userId, String userName, String password, String creatorEmail) {
        this.userEmail = userEmail;
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.creatorEmail = creatorEmail;
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

    public String getCreatorEmail() {
        return creatorEmail;
    }

    public void setCreatorEmail(String creatorEmail) {
        this.creatorEmail = creatorEmail;
    }

    @Override
    public String toString() {
        return "User{" +
                "userEmail='" + userEmail + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", creatorEmail='" + creatorEmail + '\'' +
                '}';
    }
}
