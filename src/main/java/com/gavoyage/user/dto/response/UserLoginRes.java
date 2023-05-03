package com.gavoyage.user.dto.response;

import lombok.Data;

@Data
public class UserLoginRes {
	private Long userIdx;
	private String email;
	private String userPassword;
}
