package com.gavoyage.likes.service;

import com.gavoyage.likes.domain.Likes;

public interface LikesService {
	Character pushLikes(Long reviewIdx, Long userIdx) throws Exception;
	Likes findLikesByReviewIdx(Long reviewIdx, Long userIdx) throws Exception;
	int hasLikes(Long reviewIdx, Long userIdx) throws Exception;
}
