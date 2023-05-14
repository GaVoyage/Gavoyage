package com.gavoyage.user.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gavoyage.mapper.UserMapper;
import com.gavoyage.user.domain.Users;
import com.gavoyage.user.dto.request.UserJoinReq;
import com.gavoyage.user.dto.request.UserLoginReq;

import lombok.RequiredArgsConstructor;

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
	public Users findByUserEmail(String email) {
		return userMapper.findByUserEmail(email);
	}
	
}
