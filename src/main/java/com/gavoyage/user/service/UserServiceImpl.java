package com.gavoyage.user.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.gavoyage.mapper.UserMapper;
import com.gavoyage.user.domain.User;
import com.gavoyage.user.dto.request.UserLoginReq;
import com.gavoyage.user.dto.response.UserLoginRes;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserMapper userMapper;

	@Override
	public UserLoginRes login(UserLoginReq userLoginReq) throws SQLException {
		return userMapper.login(userLoginReq);
	}

	@Override
	public int emailCheck(String email) throws Exception {
		return userMapper.emailCheck(email);
	}

	@Override
	public User findOne(Long userIdx) throws SQLException {
		return userMapper.findOne(userIdx);
	}
	
	
}
