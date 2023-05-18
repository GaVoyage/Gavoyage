package com.gavoyage.config.oauth.userinfo;

import java.util.Map;

import com.gavoyage.config.oauth.OAuth2UserInfo;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo{
	
	public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
		super(attributes);
	}

	@Override
	public String getId() { 
		return String.valueOf(attributes.get("id")); // 카카오는 Long type을 return하기 때문에 String.valueOf()로 캐스팅
	}

	@Override
	public String getNickName() {
		Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
		
		if(kakaoAccount == null) {
			return null;
		}
		
		Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("kakao_account");
		
		if(profile == null) {
			return null;
		}
		
		return (String) profile.get("nickname");
	}
}
