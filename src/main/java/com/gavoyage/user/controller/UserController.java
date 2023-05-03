package com.gavoyage.user.controller;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gavoyage.user.domain.User;
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
	
	@PostMapping("/login")
	public ResponseEntity<Long> login(@RequestBody UserLoginReq userLoginReq) throws SQLException {
		try {
			log.debug("/user/login");
			log.debug("userLoginReq : " + userLoginReq);
			
			UserLoginRes findUser = userService.login(userLoginReq);
			log.debug("findUser : " + findUser);
			if(findUser == null) { // email과 password가 일치하는 사용자가 없을 경우
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
			Long findUserIdx = findUser.getUserIdx();
			return new ResponseEntity<>(findUserIdx, HttpStatus.OK);
		}catch(Exception exception) {
			log.debug("error" + exception);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/emailCheck/{email}")
	public ResponseEntity<Integer> emailCheck(@PathVariable("email") String email) throws Exception {
		log.debug("emailCheck email : {}", email);
		int cnt = userService.emailCheck(email);
		return new ResponseEntity<>(cnt, HttpStatus.OK);
	}
	
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
}
