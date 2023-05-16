package com.gavoyage.config.oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUserOAuthRes {
	private Long userIdx;
	private String jwtToken;
}
