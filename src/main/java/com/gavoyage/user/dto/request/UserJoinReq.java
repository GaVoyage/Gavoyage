package com.gavoyage.user.dto.request;

import lombok.Data;

@Data
public class UserJoinReq {
	private String nickname;
	private String email;
	private String userPassword;
	private String phoneNumber;
}
