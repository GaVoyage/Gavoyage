package com.gavoyage.user.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gavoyage.config.login.PrincipalDetails;
import com.gavoyage.exception.RestException;
import com.gavoyage.exception.errorcode.ErrorCode;
import com.gavoyage.user.domain.Users;
import com.gavoyage.user.dto.request.UpdateNicknameReq;
import com.gavoyage.user.dto.request.UpdateUserImageUrlReq;
import com.gavoyage.user.dto.request.UserJoinReq;
import com.gavoyage.user.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor // 생성자 주입
@RequestMapping("/users")
public class UserController {
	
	private final UserServiceImpl userService;
	private final BCryptPasswordEncoder passwordEncoder;

	/**
	 * 회원 가입
	 */
	@PostMapping("/join")
	public ResponseEntity<Void> join(@RequestBody UserJoinReq userJoinReq) throws Exception{
		
		log.debug("/user/join");
		
		String email = userJoinReq.getEmail();
		if(userService.emailCheck(email) == 1) {
			log.debug("이미 있는 이메일 입니다.");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		// 유저 패스워드 암호화
		String userPassword =  passwordEncoder.encode(userJoinReq.getUserPassword());
		
		userJoinReq.setUserPassword(userPassword);
		userService.join(userJoinReq);
		
		return new ResponseEntity<>(HttpStatus.OK);	
	}
	
	/**
	 * 이메일 중복 검사
	 * 이미 존재하는 이메일이면 cnt가 1
	 * 존재하지 않는다면 cnt가 0
	 */
	@GetMapping("/emailCheck/{email}")
	public ResponseEntity<Integer> emailCheck(@PathVariable("email") String email) {
		log.debug("emailCheck email : {}", email);
		int cnt = userService.emailCheck(email);
		return new ResponseEntity<>(cnt, HttpStatus.OK);
	}
	
	@GetMapping("/nicknameCheck/{nickname}")
	public ResponseEntity<Integer> nicknameCheck(@PathVariable String nickname) {
		return new ResponseEntity<>(userService.nicknameCheck(nickname), HttpStatus.OK);
	}
	
	@GetMapping("/findbyuseremail/{email}")
	public ResponseEntity<Users> findbyuseremail(@PathVariable String email) {
		return new ResponseEntity<>(userService.findByUserEmail(email).orElseThrow(() -> new RestException(ErrorCode.RESOURCE_NOT_FOUND)), HttpStatus.OK);
	}
	
	/**
	 * 유저 정보 조회
	 */
	@GetMapping("/login")
	public ResponseEntity<Users> findOne(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		try {
			Users findUser = userService.findByUserIdx(principalDetails.getUserIdx()).orElseThrow(() -> new RestException(ErrorCode.RESOURCE_NOT_FOUND));
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
	public ResponseEntity<List<Users>> findAll() {
		try {
			List<Users> findUsers = userService.findAll();
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
	
	@DeleteMapping("/{userIdx}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long userIdx) {
		userService.deleteUser(userIdx);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping()
	public ResponseEntity<Void> quitUser(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		userService.deleteUser(principalDetails.getUserIdx());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/update/nickname")
	public ResponseEntity<Void> updateNickname(@RequestBody UpdateNicknameReq updateNicknameReq, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		updateNicknameReq.setUserIdx(principalDetails.getUserIdx());
		userService.updateNickname(updateNicknameReq);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/update/userimageurl")
	public ResponseEntity<Void> updateUserImageUrl(@RequestBody UpdateUserImageUrlReq updateUserImageUrlReq, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		updateUserImageUrlReq.setUserIdx(principalDetails.getUserIdx());
		userService.updateUserImageUrl(updateUserImageUrlReq);
		return new ResponseEntity<>(HttpStatus.OK);
	} 
	
}
