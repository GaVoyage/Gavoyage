package com.gavoyage.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gavoyage.user.domain.Users;
import com.gavoyage.user.dto.SocialJoinDto;
import com.gavoyage.user.dto.request.UserJoinReq;
import com.gavoyage.user.dto.request.UserLoginReq;
import com.gavoyage.user.mapper.UserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserMapper userMapper;

	@Override
	public Users login(UserLoginReq userLoginReq) throws Exception {
		return userMapper.login(userLoginReq);
	}

	@Override
	public int emailCheck(String email) throws Exception {
		return userMapper.emailCheck(email);
	}

	@Override
	public Users findByUserIdx(Long userIdx) throws Exception {
		return userMapper.findByUserIdx(userIdx);
	}

	@Override
	public List<Users> findAll() throws Exception {
		return userMapper.findAll();
	}

	@Override
	public void join(UserJoinReq userJoinReq) throws Exception {
		userMapper.join(userJoinReq);
	}

	@Override
	public void deleteUser(Long userIdx) throws Exception {
		userMapper.deleteUser(userIdx);
	}

	@Override
	public Optional<Users> findByUserEmail(String email){
		return Optional.ofNullable(userMapper.findByUserEmail(email));
	}

	@Override
	public void updateRefreshToken(String email, String refreshToken){
		userMapper.updateRefreshToken(email, refreshToken);
	}

	@Override
	public Users findByRefreshToken(String refreshToken){
		return userMapper.findByRefreshToken(refreshToken);
	}

	@Override
	public Optional<Users> findBySocialIdAndSocialType(String socialId, String socialType){
		return userMapper.findBySocialIdAndSocialType(socialId, socialType);
	}

	@Override
	public Long socialJoin(SocialJoinDto socialJoinDto) {
//		log.debug(socialJoinDto.toString());
		try {
			userMapper.socialJoin(socialJoinDto);
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		
		return socialJoinDto.getUserIdx();
	}

	@Override
	public String findUserNicknameByReviewIdx(Long reviewIdx) {
		return userMapper.findUserNameByReviewIdx(reviewIdx);
	}
	
}
