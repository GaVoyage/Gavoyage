package com.gavoyage.user.domain;

import com.gavoyage.config.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity{
	private Long userIdx;
	private String email;
	private String nickname;
	private String userPassword;
	private String userImageUrl;
	private String phoneNumber;
	private String userRole;
}
