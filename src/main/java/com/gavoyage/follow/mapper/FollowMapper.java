package com.gavoyage.follow.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.gavoyage.follow.dto.request.CreateFollowReq;

@Mapper
public interface FollowMapper {
	
	void createFollow(CreateFollowReq createFollowReq) throws SQLException;
	void follow(Long followIdx);
	void unfollow(Long followIdx);
	
}
