package com.gavoyage.likes.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.gavoyage.likes.domain.Likes;

@Mapper
public interface LikesMapper {
	
	void createLikes(Long reviewIdx, Long userIdx);
	Likes findLikesByReviewIdx(Long reviewIdx, Long userIdx);
	int hasLikes(Long reviewIdx, Long userIdx);
	void like(Long likeIdx);
	void dislike(Long likeIdx);
	
}
