package com.gavoyage.config.oauth.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.gavoyage.config.jwt.service.JwtService;
import com.gavoyage.config.oauth.CustomOAuth2User;
import com.gavoyage.user.mapper.UserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler{
	
	private final JwtService jwtService;
	private final UserMapper userMapper;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.debug("소셜 로그인 성공");
		
		CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
		
		String accessToken = jwtService.createAccessToken(oAuth2User.getEmail(), oAuth2User.getNickname());
		String refreshToken = jwtService.createRefreshToken();
		
		userMapper.updateRefreshToken(oAuth2User.getEmail(), refreshToken);
		
		String uri = UriComponentsBuilder.fromUriString("http://localhost:8080/")
                .queryParam("accessToken", accessToken)
                .queryParam("refreshToken", refreshToken)
                .build().toUriString();
        response.sendRedirect(uri);
	}
	
}
