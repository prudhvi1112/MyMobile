package com.mymobile.util;

import java.time.LocalDateTime;

public class UserLoginResponse {
    private String userId;

    private LocalDateTime userRegsiterDate;

    private LocalDateTime userLastLoginIn;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getUserRegsiterDate() {
        return userRegsiterDate;
    }

    public void setUserRegsiterDate(LocalDateTime userRegsiterDate) {
        this.userRegsiterDate = userRegsiterDate;
    }

    public LocalDateTime getUserLastLoginIn() {
        return userLastLoginIn;
    }

    public void setUserLastLoginIn(LocalDateTime userLastLoginIn) {
        this.userLastLoginIn = userLastLoginIn;
    }
}
