package com.mymobile.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userId;
    private String password;



    private LocalDateTime loggedInTimeStamp;


    private LocalDateTime updateInTimeStamp;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getLoggedInTimeStamp() {
        return loggedInTimeStamp;
    }

    public void setLoggedInTimeStamp(LocalDateTime loggedInTimeStamp) {
        this.loggedInTimeStamp = loggedInTimeStamp;
    }

    public LocalDateTime getUpdateInTimeStamp() {
        return updateInTimeStamp;
    }

    public void setUpdateInTimeStamp(LocalDateTime updateInTimeStamp) {
        this.updateInTimeStamp = updateInTimeStamp;
    }

    public User(int id, String userId, LocalDateTime loggedInTimeStamp, LocalDateTime updateInTimeStamp) {
        this.id = id;
        this.userId = userId;
        this.loggedInTimeStamp = loggedInTimeStamp;
        this.updateInTimeStamp = updateInTimeStamp;
    }

    public User() {
    }
}
