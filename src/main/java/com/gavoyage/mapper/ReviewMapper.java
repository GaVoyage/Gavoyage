package com.gavoyage.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gavoyage.region.domain.AttractionInfo;
import com.gavoyage.review.domain.Review;
import com.gavoyage.review.dto.request.CreateReviewReq;
import com.gavoyage.review.dto.sql.CreateRecommendDto;
import com.gavoyage.review.dto.sql.FindReviewInfo;

@Mapper
public interface ReviewMapper {
	
	int hasReview(Long planIdx) throws SQLException;
	void createReview(CreateReviewReq createReviewReq);
	void createRecommend(CreateRecommendDto createRecommendDto);
	void createUnRecommend(CreateRecommendDto createRecommendDto);
	
	Review findReview(Long reviewIdx);
	FindReviewInfo findReviewInfoByPlanIdx(Long planIdx);
	List<AttractionInfo> getRecommendsAttractionInfo(Long reviewIdx);
	List<AttractionInfo> getUnRecommendsAttractionInfo(Long reviewIdx);
	
	void deleteReview(Long reviewIdx);
	void deleteRecommend(Long reviewIdx);
	void deleteUnRecommend(Long reviewIdx);
	
}
