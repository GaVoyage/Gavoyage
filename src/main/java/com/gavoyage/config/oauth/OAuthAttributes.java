package com.gavoyage.config.oauth;

import java.util.Map;

import com.gavoyage.config.oauth.userinfo.KakaoOAuth2UserInfo;
import com.gavoyage.config.oauth.userinfo.NaverOAuth2UserInfo;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Builder
@Slf4j
public class OAuthAttributes {
	
	private String socialKey;
	private OAuth2UserInfo oAuthUserInfo;
	
	public static OAuthAttributes of(String socialType, String socialKey, Map<String, Object> attributes) {
		if(socialType.equals("naver")) {
			return ofNaver(socialKey, attributes);
		}
		if(socialType.equals("kakao")) {
			return ofKakao(socialKey, attributes);
		}
		throw new IllegalStateException("해당하는 도메인이 존재하지 않습니다");
	}

	private static OAuthAttributes ofKakao(String socialKey, Map<String, Object> attributes) {
		return OAuthAttributes.builder()
				.socialKey(socialKey)
				.oAuthUserInfo(new KakaoOAuth2UserInfo(attributes))
				.build();
	}

	private static OAuthAttributes ofNaver(String socialKey, Map<String, Object> attributes) {
		log.debug("naver userinfo : " + new NaverOAuth2UserInfo(attributes).toString());
		
		return OAuthAttributes.builder()
				.socialKey(socialKey)
				.oAuthUserInfo(new NaverOAuth2UserInfo(attributes))
				.build();
	}
}
