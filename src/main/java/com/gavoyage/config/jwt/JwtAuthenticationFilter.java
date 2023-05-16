package com.gavoyage.config.jwt;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gavoyage.config.auth.PrincipalDetails;
import com.gavoyage.user.domain.Users;
import com.gavoyage.user.dto.request.UserLoginReq;
import com.google.gson.JsonObject;

import lombok.RequiredArgsConstructor;

// 스프링 시큐리티는 기본적으로 로그인 요청이 오면 주소가 http://localhost:8080/login으로 오게되고 UsernamePasswordAuthenticationFilter가 동작하여
// login 요청 시 username, userpassword를 전송하면(by POST) 동작한다.
// 하지만 SecurityConfig에서 .formLogin().disable()을 적용해놨기 때문에 별도의 filter를 필요로 하여 이와 같이 사용자 정의 필터를 생성하고
// UsernamePasswordAuthenticationFilter를 상속받는다.
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private final AuthenticationManager authenticationManager;
	
	// "/login"으로 요청을 하면  username, userpassword를 받아 로그인을 시도하는 메소드
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
	
		System.out.println("JwtAuthenticationFilter : 로그인 시도");
		
		// 1. username, userpassword를 받아옴
		try {
			ObjectMapper om = new ObjectMapper();
			UserLoginReq user = om.readValue(request.getInputStream(), UserLoginReq.class);
			System.out.println(user);
			
			System.out.println("json to userLoginREq 완료 ");
			
			// UsernamePasswordAuthenticationToken 생성(로그인 정보를 지닌다)
			UsernamePasswordAuthenticationToken authenticationToken = 
					new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
			
			System.out.println("토큰 생성 완료");
			
			// authenticationManager가 생성한 토큰으로 인증(authenicate) 시도
			// PrincipalDetailsService의 loadUserByUsername을 내부적으로 호출하고 올바른 아이디, 패스워드라면 authentication을 return 한다.
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			
			// 로그인이 되었는지 principalDetails 출력하여 확인(유저가 출력된다면 로그인 성공)
			PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
			System.out.println("principalDetails.getUser() : " + principalDetails.getUser());
			
			// authentication(사용자 정보)가 session 영역에 저장됨
			// 굳이 JWT를 사용하면서 세션을 만들 이유는 없지만 유저의 권환 관리를 위해 session에 넣어줌
			return authentication;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	// attemptAuthentication에서 인증이 정상적으로 되었다면 실행되는 메소드
	// 이 메소드에서 JWT를 생성하고 response에 JWT 토큰을 담아 응답
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		System.out.println("인증이 정상적으로 완료되었습니다");
		
		PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
		
		// JWT 토큰 생성
		String jwtToken = JWT.create()
				.withSubject(principalDetails.getUser().getEmail())
				.withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME)) // 유효 기간을 10분으로 지정
				.withClaim("email", principalDetails.getUser().getEmail())
				.withClaim("nickname", principalDetails.getUser().getNickname())
				.sign(Algorithm.HMAC512(JwtProperties.SECRETE)); // 시크릿 키 설정
		
		// 토큰을 응답에 사용하는걸 Bearer 방식이라고 부른다.
		response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken); // 한칸 뛰어줘야 함에 유의!!
		ObjectMapper objectMapper = new ObjectMapper();
		String userResponse = objectMapper.writeValueAsString(new UserResponse(principalDetails.getUser().getNickname(), principalDetails.getUser().getEmail()));
		response.getWriter().print(userResponse);
	}
}
