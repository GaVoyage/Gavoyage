//package com.gavoyage.config.login.handler;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.gavoyage.config.jwt.service.JwtService;
//import com.gavoyage.config.login.PrincipalDetails;
//import com.gavoyage.config.login.UserResponse;
//import com.gavoyage.user.domain.Users;
//import com.gavoyage.user.service.UserServiceImpl;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
///**
// * 로그인 성공 시 수행될 핸들러
// * @author lio86
// *
// */
//@Slf4j
//@RequiredArgsConstructor
//public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
//	
//	private final JwtService jwtServcie;
//	private final UserServiceImpl userServcie;
//	
//	@Override
//	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//			Authentication authentication) throws IOException, ServletException {
//		
//		log.debug("LoginSuccessHandler Called");
//		
//		/**
//		 * access token, refresh token 응답에 추가
//		 */
//		PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
//		Users user = principalDetails.getUser();
//		
//		String accessToken = jwtServcie.createAccessToken(user);
//		String refreshToken = jwtServcie.createRefreshToken();
//		
//		jwtServcie.sendAccessAndRefreshToken(response, accessToken, refreshToken);
//		
//		/**
//		 * 로그인한 사용자 refresh token 값 갱신
//		 */
//		userServcie.updateRefreshToken(user.getEmail(), refreshToken);
//		
//		System.out.println("onAuthenticationSuccess called");
//		
//		ObjectMapper objectMapper = new ObjectMapper();
//		String userResponse = objectMapper.writeValueAsString(new UserResponse(user.getNickname(), user.getEmail()));
//		response.getWriter().print(userResponse);
//	}
//}
