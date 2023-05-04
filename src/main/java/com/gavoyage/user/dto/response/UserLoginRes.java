package com.gavoyage.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginRes {
	private Long userIdx;
	private String nickname;
}
