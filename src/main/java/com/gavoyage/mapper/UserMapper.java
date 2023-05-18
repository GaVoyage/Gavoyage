package com.gavoyage.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gavoyage.user.domain.Users;
import com.gavoyage.user.dto.request.UserJoinReq;
import com.gavoyage.user.dto.request.UserLoginReq;
import com.gavoyage.user.dto.response.UserLoginRes;

@Mapper
public interface UserMapper {
	
	int emailCheck(String email) throws SQLException;
	Users login(UserLoginReq userLoginReq) throws SQLException;
	Users findByUserIdx(Long userIdx) throws SQLException;
	List<Users> findAll() throws SQLException;
	void join(UserJoinReq userJoinReq)  throws SQLException;
	void deleteUser(Long userIdx);
	Users findByUserEmail(String email);
	Users findByRefreshToken(String refreshToken);
	void updateRefreshToken(String email, String refreshToken);
	
}
