package com.gavoyage.likes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gavoyage.config.login.PrincipalDetails;
import com.gavoyage.likes.service.LikesServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikesController {
	
	private final LikesServiceImpl likesService;
	
	@PostMapping("/{reviewIdx}")
	public ResponseEntity<?> pushLikes(@PathVariable Long reviewIdx, @AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception{
		log.debug("좋아요 버튼 눌림");
		
		return new ResponseEntity<>(likesService.pushLikes(reviewIdx, principalDetails.getUserIdx()), HttpStatus.OK);
	}
	
}
