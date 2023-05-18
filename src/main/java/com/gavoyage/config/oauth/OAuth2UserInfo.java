package com.gavoyage.config.oauth;

import java.util.Map;

/**
 * 소셜 로그인을 통해 가져온 사용자 정보를 담을 클래스의 틀을 규정하는 추상 클래스
 */
public abstract class OAuth2UserInfo {
	protected Map<String, Object> attributes; // authorization server로 부터 받은 데이터들을 담을 Map

	public OAuth2UserInfo(Map<String, Object> attributes) {
		super();
		this.attributes = attributes;
	}
	
	public abstract String getId(); // 소셜 도메인 식별 값
	
	public abstract String getNickName(); // authorization server로 부터 받은 유저 닉네임
}
