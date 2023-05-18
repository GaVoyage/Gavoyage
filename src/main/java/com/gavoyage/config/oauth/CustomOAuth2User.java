package com.gavoyage.config.oauth;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import lombok.Getter;
import lombok.Setter;

/**
 * 소셜 로그인 시 authorization server로 부터 받지 못하지만 서비스 내에서 필요한 정보들을 추가로 받기 위해 사용
 * DefaultOAuth2User : 스프링 세큐리티의 UserDetails와 같은 역할
 */
@Getter @Setter
public class CustomOAuth2User extends DefaultOAuth2User{
	private String email;
	private String userRole;
	
	public CustomOAuth2User(Collection<? extends GrantedAuthority> authorities, Map<String, Object> attributes,
			String nameAttributeKey, String email, String userRole) {
		super(authorities, attributes, nameAttributeKey);
		this.email = email;
		this.userRole = userRole;
	}
}
