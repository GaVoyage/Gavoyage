package com.gavoyage.review.service;

import com.gavoyage.review.domain.Review;
import com.gavoyage.review.dto.request.CreateReviewReq;
import com.gavoyage.review.dto.response.GetReviewInfoRes;
import com.gavoyage.review.dto.sql.FindReviewInfo;

public interface ReviewService {
	
	int hasReview(Long planIdx) throws Exception;
	void createReview(CreateReviewReq reviewCreateReq) throws Exception;
	GetReviewInfoRes getReviewInfo(Long reviewIdx) throws Exception;
	void deleteReview(Long reviewIdx) throws Exception;
	FindReviewInfo findReviewInfoByPlanIdx(Long planIdx);
	Review findReview(Long reviewIdx);
	
}
