package com.gavoyage.user.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gavoyage.user.domain.User;
import com.gavoyage.user.dto.request.UserJoinReq;
import com.gavoyage.user.dto.request.UserLoginReq;
import com.gavoyage.user.dto.response.UserLoginRes;
import com.gavoyage.user.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor // 생성자 주입
@Slf4j
@RequestMapping("/user")
public class UserController {
	
	private final UserServiceImpl userService;

	/**
	 * 회원 가입
	 */
	@PostMapping("/join")
	public ResponseEntity<?> join(@RequestBody UserJoinReq userJoinReq) throws Exception{
		
		log.debug("/user/join");
		userService.join(userJoinReq);
		
		return new ResponseEntity<>(HttpStatus.OK);	
	}
	
	
	/**
	 * 로그인
	 */
	@PostMapping("/login")
	public ResponseEntity<UserLoginRes> login(@RequestBody UserLoginReq userLoginReq, HttpSession session) throws Exception {
		try {
			log.debug("/user/login");
			
			User findUser = userService.login(userLoginReq);
			
			if(findUser == null) { // email과 password가 일치하는 사용자가 없을 경우
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
			session.setAttribute("user", findUser); // 세션에 유저 정보 저장
			
			return new ResponseEntity<>(new UserLoginRes(findUser.getUserIdx(), findUser.getNickname()), HttpStatus.OK);
			
		}catch(Exception exception) {
			log.debug("error" + exception);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	/**
	 * 이메일 중복 검사
	 * 이미 존재하는 이메일이면 cnt가 1
	 * 존재하지 않는다면 cnt가 0
	 */
	@GetMapping("/emailCheck/{email}")
	public ResponseEntity<Integer> emailCheck(@PathVariable("email") String email) throws Exception {
		log.debug("emailCheck email : {}", email);
		int cnt = userService.emailCheck(email);
		return new ResponseEntity<>(cnt, HttpStatus.OK);
	}
	
	/**
	 * 유저 정보 조회
	 */
	@GetMapping("/{userIdx}")
	public ResponseEntity<User> findOne(@PathVariable("userIdx") Long userIdx) throws Exception{
		try {
			User findUser = userService.findOne(userIdx);
			log.debug("findUser : " + findUser);
			
			if(findUser == null) { // userIdx에 해당하는 user가 존재하지 않는 경우
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<>(findUser, HttpStatus.OK);
		}catch(Exception exception) {
			log.debug("error" + exception);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * 모든 유저 정보 조회
	 */
	@GetMapping("")
	public ResponseEntity<List<User>> findAll() throws Exception{
		try {
			List<User> findUsers = userService.findAll();
			log.debug("findUser : " + findUsers);
			
			if(findUsers == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<>(findUsers, HttpStatus.OK);
		}catch(Exception exception) {
			log.debug("error" + exception);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
