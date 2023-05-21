package com.gavoyage.likes.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.gavoyage.likes.domain.Likes;

@Mapper
public interface LikesMapper {
	
	void createLikes(Long reviewIdx, Long userIdx) throws SQLException;
	Likes findLikesByReviewIdx(Long reviewIdx, Long userIdx) throws SQLException;
	int hasLikes(Long reviewIdx, Long userIdx) throws SQLException;
	void like(Long likeIdx);
	void dislike(Long likeIdx);
	
}
