//package com.gavoyage.config.login.handler;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler{
//	
//	@Override
//	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
//			AuthenticationException exception) throws IOException, ServletException {
//		
//		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//        response.getWriter().write("로그인 실패");
//        
//        log.info("로그인에 실패했습니다. 메시지 : {}", exception.getMessage());
//	}
//}
