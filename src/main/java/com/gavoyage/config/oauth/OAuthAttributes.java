package com.gavoyage.config.oauth;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OAuthAttributes {
	
	private String socialKey;
	private OAuth2UserInfo oAuthUserInfo;
	
	public static OAuthAttributes of(String socialType, String socialKey, Map<String, Object> attributes) {
		if(socialType.equals("naver")) {
			return ofNaver(socialKey, attributes);
		}
		if(socialType.equals("naver")) {
			return ofKakao(socialKey, attributes);
		}
		throw new IllegalStateException("해당하는 도메인이 존재하지 않습니다");
	}

	private static OAuthAttributes ofKakao(String socialKey, Map<String, Object> attributes) {
		return OAuthAttributes.builder()
				.socialKey(socialKey)
				.oAuthUserInfo(null)
				.build();
	}

	private static OAuthAttributes ofNaver(String socialKey, Map<String, Object> attributes) {
		return OAuthAttributes.builder()
				.socialKey(socialKey)
				.oAuthUserInfo(null)
				.build();
	}
}
