package com.gavoyage.config.jwt.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gavoyage.config.jwt.JwtProperties;
import com.gavoyage.config.login.PrincipalDetails;
import com.gavoyage.user.domain.Users;
import com.gavoyage.user.service.UserServiceImpl;

// 권한이나 인증이 필요한(회원용 api) url을 호출했을 경우  스프링 시큐리티의 BasicAuthenticationFilter를 무조건 거치게 된다.
// 권한이나 인증이 필요 없는 경우에는 거치지 않는다.
public class JwtAuthorizationFilterOld extends BasicAuthenticationFilter{
	
	private UserServiceImpl userService;

	public JwtAuthorizationFilterOld(AuthenticationManager authenticationManager, UserServiceImpl userService) {
		super(authenticationManager);
		this.userService = userService;
	}
	
	// 인증이나 권한 요청이 있을 경우 호출되는 메소드
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("인증이나 권한이 필요한 주소 요청");
		
		String jwtHeader = request.getHeader(JwtProperties.HEADER_STRING);
		System.out.println("jwtHeader : " + jwtHeader);
		
		if(jwtHeader == null || jwtHeader.startsWith(JwtProperties.TOKEN_PREFIX)) { // header가 없거나 Bearer 방식이 아닐 경우
			chain.doFilter(request, response);
			return;
		}
		
		// JWT 토큰 검증을 통해 정상적인 사용자인지 확인
		String jwtToken = request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX, ""); // 토큰 값만 꺼내옴
		String email = JWT.require(Algorithm.HMAC512(JwtProperties.SECRETE)).build().verify(jwtToken).getClaim("email").asString();
		System.out.println("email : " + email);
		
		// 서명이 정상적으로 된 경우
		if(email != null) {
			Users userEntity = userService.findByUserEmail(email);
			PrincipalDetails principalDetails = new PrincipalDetails(userEntity);
			System.out.println("userEntity : " + userEntity);
			
			// JWT 토큰 서명이 정상일 경우 Authentication 객체를 직접 생성한다.
			Authentication authentication = 
					new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
			
			// 위에서 만든 Authentication을 스프링 세큐리티의 세션에 강제로 저장
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			chain.doFilter(request, response);
		}
	}
}
