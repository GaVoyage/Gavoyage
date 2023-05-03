package com.gavoyage.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User{
	private Long userIdx;
	private String email;
	private String nickname;
	private String userPassword;
	private String userImageUrl;
	private String phoneNumber;
	private String userRole;
	private String status;
	private String createdAt;
	private String modifiedAt;
}
