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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gavoyage.config.login.PrincipalDetails;
import com.gavoyage.review.dto.request.CreateReviewReq;
import com.gavoyage.review.dto.response.GetReviewInfoRes;
import com.gavoyage.review.dto.sql.FindReviewInfo;
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
	
	@GetMapping
	public ResponseEntity<List<GetReviewInfoRes>> getAllReviewInfos() throws Exception {
		return new ResponseEntity<List<GetReviewInfoRes>>(reviewService.getAllReviewInfos(), HttpStatus.OK);
	}
	
	@GetMapping("/find-by-plan")
	public ResponseEntity<GetReviewInfoRes> getReviewInfoByPlanIdx(@RequestParam(defaultValue = "0") Long planIdx) throws Exception{
		FindReviewInfo findReviewInfo = reviewService.findReviewInfoByPlanIdx(planIdx);
		return new ResponseEntity<>(reviewService.getReviewInfo(findReviewInfo.getReviewIdx()), HttpStatus.OK);
	}
	
	@GetMapping("/{reviewIdx}")
	public ResponseEntity<GetReviewInfoRes> getReviewInfo(@PathVariable Long reviewIdx) throws Exception{
		return new ResponseEntity<>(reviewService.getReviewInfo(reviewIdx), HttpStatus.OK);
	}
	
	@DeleteMapping("/{reviewIdx}")
	public ResponseEntity<?> deleteReview(@PathVariable Long reviewIdx) throws Exception {
		reviewService.deleteReview(reviewIdx);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
