package com.gavoyage.review.controller;

import java.util.List;
import java.util.Optional;

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
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/reviews")
public class ReviewController {
	
	private final ReviewServiceImpl reviewService;
	
	@PostMapping("")
	public ResponseEntity<Void> createReview(@RequestBody CreateReviewReq reviewCreateReq, @AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception {
		
		if(principalDetails == null) {
			log.error("로그인 후 이용해주세요");
			throw new Exception();
		}
		
		reviewCreateReq.setUserIdx(principalDetails.getUser().getUserIdx());
		reviewCreateReq.setHit(0);
		reviewService.createReview(reviewCreateReq);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<GetReviewInfoRes>> getAllReviewInfos(@AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception {
		
		// 로그인 하지 않은 경우
		if(principalDetails == null) {
			return new ResponseEntity<>(reviewService.getAllReviewInfos(-1L), HttpStatus.OK);
		}
		
		// 로그인 한 경우
		return new ResponseEntity<>(reviewService.getAllReviewInfos(principalDetails.getUserIdx()), HttpStatus.OK);
	}
	
	@GetMapping("/find-by-plan")
	public ResponseEntity<GetReviewInfoRes> getReviewInfoByPlanIdx(@RequestParam(defaultValue = "0") Long planIdx, @AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception{
		FindReviewInfo findReviewInfo = reviewService.findReviewInfoByPlanIdx(planIdx);
		
		// 로그인 하지 않은 경우
		if(principalDetails == null) {
			return new ResponseEntity<>(reviewService.getReviewInfo(findReviewInfo.getReviewIdx(), -1L), HttpStatus.OK);	
		}
		
		// 로그인 한 경우
		return new ResponseEntity<>(reviewService.getReviewInfo(findReviewInfo.getReviewIdx(), principalDetails.getUserIdx()), HttpStatus.OK);
	}
	
	@GetMapping("/{reviewIdx}")
	public ResponseEntity<GetReviewInfoRes> getReviewInfo(@PathVariable Long reviewIdx, @AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception{
		
		// 로그인 하지 않은 경우
		if(principalDetails == null) {
			return new ResponseEntity<>(reviewService.getReviewInfo(reviewIdx, -1L), HttpStatus.OK);
		}
		
		// 로그인 한 경우
		return new ResponseEntity<>(reviewService.getReviewInfo(reviewIdx, principalDetails.getUserIdx()), HttpStatus.OK);
	}
	
	@DeleteMapping("/{reviewIdx}")
	public ResponseEntity<Void> deleteReview(@PathVariable Long reviewIdx) throws Exception {
		reviewService.deleteReview(reviewIdx);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
