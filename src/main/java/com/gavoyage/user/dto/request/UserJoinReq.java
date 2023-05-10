package com.gavoyage.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserJoinReq {
	private String nickname;
	private String email;
	private String userPassword;
	private String phoneNumber;
}
