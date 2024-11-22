package com.mymobile.util;

import java.time.LocalDateTime;

public class UserResponse {

    private String userId;
    private LocalDateTime loggedInTimeStamp;
    private LocalDateTime updateInTimeStamp;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
}
