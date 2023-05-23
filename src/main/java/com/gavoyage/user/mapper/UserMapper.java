package com.gavoyage.user.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.gavoyage.user.domain.Users;
import com.gavoyage.user.dto.SocialJoinDto;
import com.gavoyage.user.dto.request.UserJoinReq;
import com.gavoyage.user.dto.request.UserLoginReq;

@Mapper
public interface UserMapper {
	
	int emailCheck(String email);
	int nicknameCheck(String nickname);
	Users login(UserLoginReq userLoginReq);
	Users findByUserIdx(Long userIdx);
	List<Users> findAll();
	void join(UserJoinReq userJoinReq);
	void deleteUser(Long userIdx);
	Users findByUserEmail(String email);
	Users findByRefreshToken(String refreshToken);
	void updateRefreshToken(String email, String refreshToken);
	Optional<Users> findBySocialIdAndSocialType(String socialId, String socialType);
	void socialJoin(SocialJoinDto socialJoinDto);
	String findUserNameByReviewIdx(Long reviewIdx);
}
