package com.gavoyage.review.service;

import com.gavoyage.review.dto.request.CreateReviewReq;
import com.gavoyage.review.dto.response.GetReviewInfoRes;

public interface ReviewService {
	
	int hasReview(Long planIdx) throws Exception;
	void createReview(CreateReviewReq reviewCreateReq) throws Exception;
	GetReviewInfoRes getReviewInfo(Long planIdx) throws Exception;
	void deleteReview(Long reviewIdx) throws Exception;
	
}
