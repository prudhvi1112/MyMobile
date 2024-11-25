package com.mymobile.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class LogoutResponse 
{
	private String userId;
	private LocalDateTime logoutTime;

}
