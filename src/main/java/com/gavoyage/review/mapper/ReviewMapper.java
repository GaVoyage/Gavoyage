package com.gavoyage.review.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gavoyage.review.domain.Review;
import com.gavoyage.review.dto.request.CreateReviewReq;
import com.gavoyage.review.dto.sql.AttractionInfoWithIsScrab;
import com.gavoyage.review.dto.sql.CreateRecommendDto;
import com.gavoyage.review.dto.sql.FindReviewInfo;

@Mapper
public interface ReviewMapper {
	
	int hasReview(Long planIdx);
	void createReview(CreateReviewReq createReviewReq);
	void createRecommend(CreateRecommendDto createRecommendDto);
	void createUnRecommend(CreateRecommendDto createRecommendDto);
	
	Review findReview(Long reviewIdx);
	List<Review> findAllReviews();
	FindReviewInfo findReviewInfoByPlanIdx(Long planIdx);
	List<AttractionInfoWithIsScrab> getRecommendsAttractionInfo(Long reviewIdx, Long userIdx);
	List<AttractionInfoWithIsScrab> getUnRecommendsAttractionInfo(Long reviewIdx, Long userIdx);
	
	void deleteReview(Long reviewIdx);
	void deleteRecommend(Long reviewIdx);
	void deleteUnRecommend(Long reviewIdx);
	
}
