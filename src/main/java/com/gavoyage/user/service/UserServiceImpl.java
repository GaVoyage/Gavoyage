package com.gavoyage.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gavoyage.user.domain.Users;
import com.gavoyage.user.dto.SocialJoinDto;
import com.gavoyage.user.dto.request.UpdateNicknameReq;
import com.gavoyage.user.dto.request.UpdateUserImageUrlReq;
import com.gavoyage.user.dto.request.UserJoinReq;
import com.gavoyage.user.dto.request.UserLoginReq;
import com.gavoyage.user.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService{
	
	private final UserMapper userMapper;

	@Override
	public Users login(UserLoginReq userLoginReq) {
		return userMapper.login(userLoginReq);
	}

	@Override
	public int emailCheck(String email) {
		return userMapper.emailCheck(email);
	}

	@Override
	public Optional<Users> findByUserIdx(Long userIdx) {
		return userMapper.findByUserIdx(userIdx);
	}

	@Override
	public List<Users> findAll() {
		return userMapper.findAll();
	}

	@Override
	public void join(UserJoinReq userJoinReq) {
		userMapper.join(userJoinReq);
	}

	@Override
	public void deleteUser(Long userIdx) {
		userMapper.deleteUser(userIdx);
	}

	@Override
	public Optional<Users> findByUserEmail(String email){
		return userMapper.findByUserEmail(email);
	}

	@Override
	public void updateRefreshToken(String email, String refreshToken){
		userMapper.updateRefreshToken(email, refreshToken);
	}

	@Override
	public Optional<Users> findByRefreshToken(String refreshToken){
		return userMapper.findByRefreshToken(refreshToken);
	}

	@Override
	public Optional<Users> findBySocialIdAndSocialType(String socialId, String socialType){
		return userMapper.findBySocialIdAndSocialType(socialId, socialType);
	}

	@Override
	public Long socialJoin(SocialJoinDto socialJoinDto) {
		userMapper.socialJoin(socialJoinDto);
		return socialJoinDto.getUserIdx();
	}

	@Override
	public String findUserNicknameByReviewIdx(Long reviewIdx) {
		return userMapper.findUserNameByReviewIdx(reviewIdx);
	}

	@Override
	public int nicknameCheck(String nickname) {
		return userMapper.nicknameCheck(nickname);
	}

	@Override
	public void updateNickname(UpdateNicknameReq updateNicknameReq) {
		userMapper.updateNickname(updateNicknameReq);
	}

	@Override
	public void updateUserImageUrl(UpdateUserImageUrlReq updateUserImageUrlReq) {
		userMapper.updateUserImageUrl(updateUserImageUrlReq);
	}
	
}
