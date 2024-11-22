package com.mymobile.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
public class LogoutEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private user Id;
    private String userName;
    private LocalDateTime lastLogoutTime;

    public LogoutEntity(user id, String userName, LocalDateTime lastLogoutTime) {
        Id = id;
        this.userName = userName;
        this.lastLogoutTime = lastLogoutTime;
    }

    public void setId(user id) {
        Id = id;
    }

    public String getUserName() {
        return userName;
    }

    public LogoutEntity(){

}

    @java.lang.Override
    public java.lang.String toString() {
        return "LogoutEntity{" +
                "Id=" + Id +
                ", userName='" + userName + '\'' +
                ", lastLogoutTime=" + lastLogoutTime +
                '}';
    }


