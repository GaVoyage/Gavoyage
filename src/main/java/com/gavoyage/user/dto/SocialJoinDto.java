package com.gavoyage.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SocialJoinDto {
	// authorization server로 부터 받는 정보
	private String socialType;
	private String socialId;
	private String nickname;
	
	// 임의로 지정할 데이터
	private String email;
	private String userPassword;
	private String phoneNumber;
	
	// insert 후 pk 값 받아옴
	private Long userIdx;
}
