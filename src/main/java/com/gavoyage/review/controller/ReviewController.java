package com.gavoyage.review.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gavoyage.config.login.PrincipalDetails;
import com.gavoyage.review.dto.request.CreateReviewReq;
import com.gavoyage.review.dto.response.GetReviewInfoRes;
import com.gavoyage.review.service.ReviewServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
	
	private final ReviewServiceImpl reviewService;
	
	@PostMapping("")
	public ResponseEntity<?> createReview(@RequestBody CreateReviewReq reviewCreateReq, @AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception {
		
		reviewCreateReq.setUserIdx(principalDetails.getUser().getUserIdx());
		reviewCreateReq.setHit(0);
		reviewService.createReview(reviewCreateReq);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/{planIdx}")
	public ResponseEntity<GetReviewInfoRes> getReviewInfo(@PathVariable Long planIdx) throws Exception{
		
		return new ResponseEntity<>(reviewService.getReviewInfo(planIdx), HttpStatus.OK);
	}
	
	@DeleteMapping("/{reviewIdx}")
	public ResponseEntity<?> deleteReview(@PathVariable Long reviewIdx) throws Exception {
		reviewService.deleteReview(reviewIdx);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
