package com.gavoyage.likes.service;

import com.gavoyage.likes.domain.Likes;

public interface LikesService {
	Character pushLikes(Long reviewIdx, Long userIdx);
	Likes findLikesByReviewIdx(Long reviewIdx, Long userIdx);
	int hasLikes(Long reviewIdx, Long userIdx);
}
