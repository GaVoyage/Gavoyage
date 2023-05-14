package com.gavoyage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;
import org.springframework.web.filter.CorsFilter;

import com.gavoyage.config.jwt.JwtAuthenticationFilter;
import com.gavoyage.filter.MyFilter3;

import lombok.RequiredArgsConstructor;

@Configuration // 스프링 설정 정보 
@EnableWebSecurity // 스프링 시큐리티 활성화
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final CorsFilter corsFilter;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.addFilterBefore(new MyFilter3(), SecurityContextPersistenceFilter.class);  // 두 번째 인자로 들어온 필터 이전에 수행할 사용자 정의 필터 지정
		http.csrf().disable(); // rest api는 서버에 인증 정보를 저장하지 않기 때문에 csrf를 사용하지 않아도 된다.
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션을 사용하지 않도록 설정
		.and()
		.addFilter(corsFilter)
		.formLogin().disable()
//		 HttpBasic: http 요청을 보낼 때 마다 Header 영역에  Authorization이라는 key 값으로 매번 id와 pw를 함께 보내는 방식
//		 HttpBearer: http 요청 시 Authorization이라는 key 값으로 idl, pw가 아닌 Token(ex. JWT)을 보내주는 방식  
		.httpBasic().disable() // 기본 인증 방식 비활성화
		.addFilter(new JwtAuthenticationFilter(authenticationManager())); // 
//		.authorizeHttpRequests()
//		.antMatchers("/regions/**").permitAll() // 지역정보 조회는 로그인 필요 X
//		.antMatchers("/regions/**").permitAll() // 지역정보 조회는 로그인 필요 X
//		.anyRequest().authenticated();      // 나머지 요청들은 로그인 필요
	}
	
}
