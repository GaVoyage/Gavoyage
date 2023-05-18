package com.gavoyage.config.oauth.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.gavoyage.config.oauth.OAuth2UserInfo;
import com.gavoyage.config.oauth.OAuthAttributes;
import com.gavoyage.user.domain.Users;
import com.gavoyage.user.dto.SocialJoinDto;
import com.gavoyage.user.dto.request.UserJoinReq;
import com.gavoyage.user.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>{
	private final UserServiceImpl userService;
	
	private static final String NAVER = "naver";
	private static final String KAKAO = "kakao";
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		log.debug("소셜 로그인 실행");
		
		OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);
		
		// 1. 소셜 로그인을 시도한  도메인 정보 (ex. "naver", "kakao")
		String socialType = userRequest.getClientRegistration().getRegistrationId();
		
		// 2. authorization server에서 사용하는 pk
		String socialId = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName(); 
        
        // 3. authorization server에서 가져온 사용자 정보를 소셜 도메인에 따라 구분하여 OAuthAttributes에 담아줌
        OAuthAttributes oAuthAttributes = OAuthAttributes.of(socialType, socialId, oAuth2User.getAttributes());
		
        // 4. 소셜 로그인을 시도하는 사용자가 기존에 있던 사용자인지 유무 확인
        Users user = userService.findBySocialIdAndSocialType(socialId, socialType).orElse(null);
        
//        if(user == null) { // 처음 로그인한 사용자라면 회원 가입 진행
//        	try {
//				userService.join((socialType, oAuthAttributes));
//			} catch (Exception e) {
//				log.error(e.getMessage());
//			}
//        }
//        
//        // 기존 회원이라면 로그인 계속 진행
//		return null;
//	}
//	
//	private SocialJoinDto createSocialJoinDto(String socialType, OAuthAttributes oAuthAttributes) {
//		OAuth2UserInfo oAuth2UserInfo = oAuthAttributes.getOAuthUserInfo();
//		return SocialJoinDto.builder()
//				.
//		
        return null;
	}
	
}
