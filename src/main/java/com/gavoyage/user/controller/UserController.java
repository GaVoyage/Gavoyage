package com.gavoyage.user.controller;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gavoyage.user.domain.User;
import com.gavoyage.user.dto.request.UserLoginReq;
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
	public ResponseEntity<Long> login(UserLoginReq userLoginReq) throws SQLException {
		try {
		log.debug("/user/login");
			User findUser = userService.findByEmail(userLoginReq.getEmail());
			Long findUserIdx = findUser.getUserIdx();
			if(findUser == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(findUserIdx, HttpStatus.OK);
		}catch(Exception exception) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
