package com.gavoyage.config.oauth.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.gavoyage.config.oauth.CustomOAuth2User;
import com.gavoyage.config.oauth.OAuth2UserInfo;
import com.gavoyage.config.oauth.OAuthAttributes;
import com.gavoyage.config.oauth.userinfo.KakaoOAuth2UserInfo;
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

	private SocialJoinDto socialJoinDto;
	
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

        log.debug("socialType : " + socialType);
        log.debug("socialId : " + socialId);
        log.debug(oAuthAttributes.getOAuthUserInfo().toString());
        
        // 4. 소셜 로그인을 시도하는 사용자가 기존에 있던 사용자인지 유무 확인
        Users user = userService.findBySocialIdAndSocialType(socialId, socialType).orElse(null);
        
        
        // 5. 처음 로그인한 사용자라면 회원 가입 진행
        if(user == null) {
        	try {
        		log.debug("5. 처음 로그인한 사용자라면 회원 가입 진행");
        		SocialJoinDto socialJoinDto = createSocialJoinDto(socialType, oAuthAttributes);
        		log.debug("SocialJoinDto : " +  socialJoinDto.toString());
        		Long userIdx = userService.socialJoin(createSocialJoinDto(socialType, oAuthAttributes));
				user = userService.findByUserIdx(userIdx);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
        }
        
        // 6. 로그인 성공 시 유저 정보를 담은 CustomOAuth2User return
        return new CustomOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getUserRole())),
                oAuth2User.getAttributes(),
                oAuthAttributes.getSocialKey(),
                user.getEmail(),
                user.getUserRole(),
                user.getNickname()
        );
        
        
	}
	
	/**
	 * 회원 가입시 사용할 dto를 userIdx를 제외하고 나머지 값들을 채워주고 return하는 메소드(userIdx는 socialJoin 호출 시 return할 때 받아온다)
	 * 소셜 로그인에서는 사용하지 않는 User 테이블의 not null 컬럼을 채워주기 위해 email, password, phoneNumber는 임의로 지정
	 * @param socialType
	 * @param oAuthAttributes
	 * @return
	 */
	private SocialJoinDto createSocialJoinDto(String socialType, OAuthAttributes oAuthAttributes) {
		OAuth2UserInfo oAuth2UserInfo = oAuthAttributes.getOAuthUserInfo();
		log.debug("oAuth2User : " + oAuth2UserInfo.toString());
		log.debug("id : {} ", oAuth2UserInfo.getId());
		log.debug("nickname : ", oAuth2UserInfo.getNickName());
		log.debug("email : ", oAuth2UserInfo.getEmail());
		
		
		String email = oAuth2UserInfo.getEmail();
		if(email == null) {
			email = "social@ssafy.com";
		}
	
		
		return SocialJoinDto.builder()
				.socialType(socialType)
				.socialId(oAuth2UserInfo.getId())
				.nickname(oAuth2UserInfo.getNickName())
				.email(email)
				.userPassword("1234")
				.phoneNumber("010-1234-5678")
				.build();
	}
	
}
