package com.gavoyage.review.service;

import org.springframework.stereotype.Service;
import com.gavoyage.mapper.ReviewMapper;
import com.gavoyage.review.domain.Review;
import com.gavoyage.review.dto.request.CreateReviewReq;
import com.gavoyage.review.dto.response.GetReviewInfoRes;
import com.gavoyage.review.dto.sql.CreateRecommendDto;
import com.gavoyage.review.dto.sql.FindReviewInfo;
import com.gavoyage.user.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
	
	private final ReviewMapper reviewMapper;
	private final UserServiceImpl userService;
	
	@Override
	public int hasReview(Long planIdx) throws Exception {
		return reviewMapper.hasReview(planIdx);
	}

	@Override
	public void createReview(CreateReviewReq reviewCreateReq) throws Exception {
		reviewMapper.createReview(reviewCreateReq);
				
		log.debug("reviewIdx : {}", reviewCreateReq.getReviewIdx());
		
		reviewCreateReq.getRecommendAttractions()
											.stream()
											.forEach(content_id -> reviewMapper.createRecommend(
												CreateRecommendDto.builder()
												.reviewIdx(reviewCreateReq.getReviewIdx())
												.content_id(content_id)
												.build()));
														
		
		reviewCreateReq.getUnRecommendAttractions()
											.stream()
											.forEach(content_id -> reviewMapper.createUnRecommend(
												CreateRecommendDto.builder()
												.reviewIdx(reviewCreateReq.getReviewIdx())
												.content_id(content_id)
												.build()));
	}
	
	
	@Override
	public GetReviewInfoRes getReviewInfo(Long reviewIdx) throws Exception {		
		Review review = findReview(reviewIdx);
		return GetReviewInfoRes.builder()
								.reviewIdx(reviewIdx)
								.writerName(userService.findUserNicknameByReviewIdx(reviewIdx))
								.title(review.getTitle())
								.contents(review.getContents())
								.hit(review.getHit())
								.createdAt(review.getCreatedAt())
								.recommendsAttractionInfo(reviewMapper.getRecommendsAttractionInfo(reviewIdx))
								.unrecommendsAttractionInfo(reviewMapper.getUnRecommendsAttractionInfo(reviewIdx))
								.build();
		
	}

	@Override
	public void deleteReview(Long reviewIdx) throws Exception {
		reviewMapper.deleteReview(reviewIdx);
		reviewMapper.deleteRecommend(reviewIdx);
		reviewMapper.deleteUnRecommend(reviewIdx);
	}

	@Override
	public FindReviewInfo findReviewInfoByPlanIdx(Long planIdx) {
		return reviewMapper.findReviewInfoByPlanIdx(planIdx);
	}

	@Override
	public Review findReview(Long reviewIdx) {
		return reviewMapper.findReview(reviewIdx);
	}
	
}
