package com.gavoyage.review.service;

import java.util.List;

import com.gavoyage.review.domain.Review;
import com.gavoyage.review.dto.request.CreateReviewReq;
import com.gavoyage.review.dto.response.GetReviewInfoLikesRes;
import com.gavoyage.review.dto.response.GetReviewInfoRes;
import com.gavoyage.review.dto.sql.FindReviewInfo;

public interface ReviewService {
	
	int hasReview(Long planIdx) throws Exception;
	void createReview(CreateReviewReq reviewCreateReq) throws Exception;
	GetReviewInfoRes getReviewInfo(Long reviewIdx, Long userIdx) throws Exception;
	List<GetReviewInfoRes> getAllReviewInfos(Long userIdx) throws Exception;
	void deleteReview(Long reviewIdx) throws Exception;
	FindReviewInfo findReviewInfoByPlanIdx(Long planIdx);
	Review findReview(Long reviewIdx);
	List<GetReviewInfoLikesRes> getReviewInfoLikes(Long userIdx);
	List<GetReviewInfoRes> getReviewInfosByUserIdx(Long userIdx);
	
}
