package com.gavoyage.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gavoyage.user.domain.User;
import com.gavoyage.user.dto.request.UserJoinReq;
import com.gavoyage.user.dto.request.UserLoginReq;
import com.gavoyage.user.dto.response.UserLoginRes;

@Mapper
public interface UserMapper {
	
	int emailCheck(String email) throws SQLException;
	User login(UserLoginReq userLoginReq) throws SQLException;
	User findOne(Long userIdx) throws SQLException;
	List<User> findAll() throws SQLException;
	void join(UserJoinReq userJoinReq)  throws SQLException;
	void deleteUser(Long userIdx);

}
