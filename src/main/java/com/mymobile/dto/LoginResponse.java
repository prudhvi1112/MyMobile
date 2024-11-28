package com.mymobile.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class LoginResponse
{
	
 private String userId;
 private String userName;
 private String userRole;
 private LocalDateTime lastLoginTime;
 private LocalDateTime loginTime;

}
