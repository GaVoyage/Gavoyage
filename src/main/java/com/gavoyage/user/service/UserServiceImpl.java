package com.gavoyage.user.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.gavoyage.mapper.UserMapper;
import com.gavoyage.user.domain.User;
import com.gavoyage.user.dto.request.UserLoginReq;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserMapper userMapper;

	@Override
	public User findByEmail(String email) throws SQLException {
		return userMapper.findByEmail(email);
	}
	
	
}
