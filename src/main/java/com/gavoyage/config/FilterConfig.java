//package com.gavoyage.config;
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.gavoyage.filter.MyFilter1;
//
///**
// * @author lio86
// * 사용자 정의 필터 등록
// * 굳이 스프링 시큐리티 필터 안에 등록하지 않기 위해 사용한다.
// */
//@Configuration
//public class FilterConfig {
//
//	@Bean
//	public FilterRegistrationBean<MyFilter1> filter1(){ // MyFilter1 등록
//		FilterRegistrationBean<MyFilter1> bean = new FilterRegistrationBean<MyFilter1>(new MyFilter1());
//		bean.addUrlPatterns("/*"); // 모든 요청에 필터 적용
//		bean.setOrder(0); // 낮은 번호 순으로 필터가 실행된다.
//		return bean;
//	}
//}
