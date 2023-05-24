package com.gavoyage.user.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.gavoyage.user.domain.Users;
import com.gavoyage.user.dto.SocialJoinDto;
import com.gavoyage.user.dto.request.UpdateNicknameReq;
import com.gavoyage.user.dto.request.UpdateUserImageUrlReq;
import com.gavoyage.user.dto.request.UserJoinReq;
import com.gavoyage.user.dto.request.UserLoginReq;

@Mapper
public interface UserMapper {
	
	int emailCheck(String email);
	int nicknameCheck(String nickname);
	
	Users login(UserLoginReq userLoginReq);
	void join(UserJoinReq userJoinReq);
	
	// 조회
	Optional<Users> findByUserIdx(Long userIdx);
	List<Users> findAll();
	String findUserNameByReviewIdx(Long reviewIdx);
	Optional<Users> findByUserEmail(String email);
	Optional<Users> findByRefreshToken(String refreshToken);
	void updateRefreshToken(String email, String refreshToken);
	
	// 소셜 로그인
	Optional<Users> findBySocialIdAndSocialType(String socialId, String socialType);
	void socialJoin(SocialJoinDto socialJoinDto);
	
	void deleteUser(Long userIdx);	
	void updateNickname(UpdateNicknameReq updateNicknameReq);
	void updateUserImageUrl(UpdateUserImageUrlReq updateUserImageUrlReq);
	
}
