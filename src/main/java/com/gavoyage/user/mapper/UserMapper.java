package com.gavoyage.user.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.gavoyage.user.domain.Users;
import com.gavoyage.user.dto.SocialJoinDto;
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
	Optional<Users> findBySocialIdAndSocialType(String socialId, String socialType);
	void socialJoin(SocialJoinDto socialJoinDto) throws SQLException;
	String findUserNameByReviewIdx(Long reviewIdx);
}
