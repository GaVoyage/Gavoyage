package com.gavoyage.review.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gavoyage.likes.mapper.LikesMapper;
import com.gavoyage.review.domain.Review;
import com.gavoyage.review.dto.request.CreateReviewReq;
import com.gavoyage.review.dto.response.GetReviewInfoRes;
import com.gavoyage.review.dto.sql.CreateRecommendDto;
import com.gavoyage.review.dto.sql.FindReviewInfo;
import com.gavoyage.review.mapper.ReviewMapper;
import com.gavoyage.user.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
	
	private final ReviewMapper reviewMapper;
	private final UserServiceImpl userService;
	private final LikesMapper likesMapper;
	
	@Override
	public int hasReview(Long planIdx) {
		return reviewMapper.hasReview(planIdx);
	}

	@Override
	public void createReview(CreateReviewReq reviewCreateReq) {
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
	public GetReviewInfoRes getReviewInfo(Long reviewIdx, Long userIdx) {		
		return createGetReviewInfoRes(findReview(reviewIdx), userIdx);
	}

	@Override
	public void deleteReview(Long reviewIdx) {
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

	@Override
	public List<GetReviewInfoRes> getAllReviewInfos(Long userIdx) {
		return reviewMapper.findAllReviews().stream().map(r -> createGetReviewInfoRes(r, userIdx)).collect(Collectors.toList());
	}
	
	private GetReviewInfoRes createGetReviewInfoRes(Review review, Long userIdx) {
		return GetReviewInfoRes.builder()
				.reviewIdx(review.getReviewIdx())
				.writerName(userService.findUserNicknameByReviewIdx(review.getReviewIdx()))
				.title(review.getTitle())
				.contents(review.getContents())
				.hit(review.getHit())
				.isLiked(likesMapper.hasLikes(review.getReviewIdx(), userIdx))
				.createdAt(review.getCreatedAt())
				.recommendsAttractionInfo(reviewMapper.getRecommendsAttractionInfo(review.getReviewIdx(), userIdx))
				.unrecommendsAttractionInfo(reviewMapper.getUnRecommendsAttractionInfo(review.getReviewIdx(), userIdx))
				.build();
	}
}
