package com.gavoyage.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFilter3 implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("필터3");
		
		/**
		 * Http 요청의 header의 key가 "Authorization"인 value에 들어있는 token을
		 * 내가 만든 토큰이 맞는지 검증(by RSA, HS256)
		 */
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
			
		if(req.getMethod().equals("POST")) { // POST 요청이 들어오는 경우
			String headerAuth = req.getHeader("Authorization");
			
			if(headerAuth.equals("cos")) { // 토큰이 유효하면
				chain.doFilter(req, res);
			}else {
				PrintWriter out = res.getWriter();
				out.println("인증 안됨");
			}
		}
	}
	

}
