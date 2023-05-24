package com.gavoyage.user.service;

import java.util.List;
import java.util.Optional;

import com.gavoyage.user.domain.Users;
import com.gavoyage.user.dto.SocialJoinDto;
import com.gavoyage.user.dto.request.UpdateNicknameReq;
import com.gavoyage.user.dto.request.UpdateUserImageUrlReq;
import com.gavoyage.user.dto.request.UserJoinReq;
import com.gavoyage.user.dto.request.UserLoginReq;

public interface UserService {
	
	int emailCheck(String email);
	int nicknameCheck(String nickname);
	Users login(UserLoginReq userLoginReq);
	void join(UserJoinReq userJoinReq);
	Optional<Users> findByUserIdx(Long userIdx);
	List<Users> findAll();
	void deleteUser(Long userIdx);
	Optional<Users> findByUserEmail(String email);
	void updateRefreshToken(String email, String refreshToken);
	Optional<Users> findByRefreshToken(String refreshToken);
	Optional<Users> findBySocialIdAndSocialType(String socialId, String socialType);
	Long socialJoin(SocialJoinDto socialJoinDto);
	String findUserNicknameByReviewIdx(Long reviewIdx);
	void updateNickname(UpdateNicknameReq updateNicknameReq);
	void updateUserImageUrl(UpdateUserImageUrlReq updateUserImageUrlReq);
	
}
