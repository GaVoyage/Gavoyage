package com.gavoyage.config.oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUserOAuthReq {
	private String email;
	private String password;
	private String nickname;
}
