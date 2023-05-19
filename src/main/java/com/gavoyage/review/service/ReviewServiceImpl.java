package com.gavoyage.review.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import com.gavoyage.mapper.ReviewMapper;
import com.gavoyage.mapper.UserMapper;
import com.gavoyage.region.domain.AttractionInfo;
import com.gavoyage.region.service.RegionServiceImpl;
import com.gavoyage.review.dto.request.CreateReviewReq;
import com.gavoyage.review.dto.response.GetReviewInfoRes;
import com.gavoyage.review.dto.sql.CreateRecommendDto;
import com.gavoyage.review.dto.sql.FindReviewInfo;

import io.github.classgraph.HasName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
	
	private final ReviewMapper reviewMapper;
	private final RegionServiceImpl regionService;
	
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
	public GetReviewInfoRes getReviewInfo(Long planIdx) throws Exception {		
		FindReviewInfo findReviewInfo = reviewMapper.findReviewInfoByPlanIdx(planIdx);
		
		return GetReviewInfoRes.builder()
								.reviewIdx(findReviewInfo.getReviewIdx())
								.title(findReviewInfo.getTitle())
								.contents(findReviewInfo.getContents())
								.hit(findReviewInfo.getHit())
								.recommendsAttractionInfo(reviewMapper.getRecommendsAttractionInfo(planIdx))
								.unrecommendsAttractionInfo(reviewMapper.getUnRecommendsAttractionInfo(planIdx))
								.build();
		
	}

	@Override
	public void deleteReview(Long reviewIdx) throws Exception {
		reviewMapper.deleteReview(reviewIdx);
		reviewMapper.deleteRecommend(reviewIdx);
		reviewMapper.deleteUnRecommend(reviewIdx);
	}
	
}
