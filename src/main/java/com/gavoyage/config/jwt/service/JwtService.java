package com.gavoyage.config.jwt.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gavoyage.user.domain.Users;
import com.gavoyage.user.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {
	
	private final UserServiceImpl userService;
	
	/**
	 * @Value 어노테이션을 사용해 application-jwt.yml에 담긴 jwt 관련 설정 정보들을 불러옴
	 */
	@Value("${jwt.secretKey}")
    private String secretKey;
	
	@Value("${jwt.tokenPrefix}")
	private String tokenPrefix;

    @Value("${jwt.access.expiration}")
    private Long accessTokenExpirationTime;

    @Value("${jwt.refresh.expiration}")
    private Long refreshTokenExpirationTime;

    @Value("${jwt.access.header}")
    private String accessHeader;

    @Value("${jwt.refresh.header}")
    private String refreshHeader;
    
   public String createAccessToken(Users user) {
	   return JWT.create()
			   .withSubject("AccessToken") // jwt의 subject를 지정해주는데 사실 아무거나 사용해도 상관 없다
			   .withExpiresAt(new Date(System.currentTimeMillis() + accessTokenExpirationTime)) // JWT 유효기간
			   .withClaim("email", user.getEmail()) // 클레임으로 유저 이메일 사용
			   .sign(Algorithm.HMAC512(secretKey)); // 시크릿 키 설정
   }
   
   public String createRefreshToken() {
	   return JWT.create()
			   .withSubject("RefreshToken") // jwt의 subject를 지정해주는데 사실 아무거나 사용해도 상관 없다
			   .withExpiresAt(new Date(System.currentTimeMillis() + refreshTokenExpirationTime)) // JWT 유효기간
			   .sign(Algorithm.HMAC512(secretKey)); // 시크릿 키 설정
   }
   
   public void sendAccessAndRefreshToken(HttpServletResponse response, String accessToken, String refreshToken) {
	   response.setContentType("application/json;charset=UTF-8"); // 응답의 content-type 지정
       response.setStatus(HttpServletResponse.SC_OK); // 응답 상태 코드 지정
       
       sendAccessToken(response, accessToken);
       sendRefreshToken(response, refreshToken);
   }
   
   public void sendAccessToken(HttpServletResponse response, String accessToken) {
	   response.setHeader(accessHeader, tokenPrefix + accessToken);
   }
   
   public void sendRefreshToken(HttpServletResponse response, String refreshToken) {
	   response.setHeader(accessHeader, tokenPrefix + refreshToken);
   }
   
   public String extractAccessToken(HttpServletRequest request) {
	   return request.getHeader(accessHeader).replace(tokenPrefix, "");
   }
   
   public String extractRefreshToken(HttpServletRequest request) {
	   return request.getHeader(refreshHeader).replace(tokenPrefix, "");
   }
   
   public String extractEmail(String accessToken){
	   return JWT.require(Algorithm.HMAC512(secretKey)) // jwt verifier builder를 불러옴
				.build()              // 불러온 jwt verifier builder로 jwt verifier 생성
				.verify(accessToken)  // access token 유효성 검증
				.getClaim("email")    // email cliam 추출
				.asString();
   }
   
   public void updateRefreshToken(String email, String refreshToken) {
	   userService.updateRefreshToken(email, refreshToken);
   }
   
   public boolean isTokenValid(String token) {
       try {
    	   // jwt verifier builder 호출 후 jwt verifier를 생성하여 검증
    	   // 이 과정에서 예외가 터진다면 토큰이 유효하지 않다는 것이다!
           JWT.require(Algorithm.HMAC512(secretKey)).build().verify(token);
           return true;
       } catch (Exception e) {
           log.error("유효하지 않은 토큰입니다. {}", e.getMessage());
           return false;
       }
   }
   
}
