package com.gavoyage.review.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.gavoyage.review.domain.Review;
import com.gavoyage.review.dto.request.CreateReviewReq;
import com.gavoyage.review.dto.response.GetReviewInfoLikesRes;
import com.gavoyage.review.dto.sql.AttractionInfoWithIsScrab;
import com.gavoyage.review.dto.sql.CreateRecommendDto;
import com.gavoyage.review.dto.sql.FindReviewInfo;

@Mapper
public interface ReviewMapper {
	
	int hasReview(Long planIdx);
	void createReview(CreateReviewReq createReviewReq);
	void createRecommend(CreateRecommendDto createRecommendDto);
	void createUnRecommend(CreateRecommendDto createRecommendDto);
	
	Optional<Review> findReview(Long reviewIdx);
	List<Review> findAllReviews();
	List<Review> findReviewsByUserIdx(Long userIdx);
	FindReviewInfo findReviewInfoByPlanIdx(Long planIdx);
	List<AttractionInfoWithIsScrab> getRecommendsAttractionInfo(Long reviewIdx);
	List<AttractionInfoWithIsScrab> getUnRecommendsAttractionInfo(Long reviewIdx);
	List<GetReviewInfoLikesRes> getReviewInfoLikes(Long userIdx);
	
	
	void deleteReview(Long reviewIdx);
	void deleteRecommend(Long reviewIdx);
	void deleteUnRecommend(Long reviewIdx);
	
}
