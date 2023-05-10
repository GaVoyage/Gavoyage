package com.gavoyage.user.service;

import java.util.List;

import com.gavoyage.user.domain.User;
import com.gavoyage.user.dto.request.UserJoinReq;
import com.gavoyage.user.dto.request.UserLoginReq;

public interface UserService {
	int emailCheck(String email) throws Exception;
	User login(UserLoginReq userLoginReq) throws Exception;
	void join(UserJoinReq userJoinReq) throws Exception;
	User findOne(Long userIdx) throws Exception;
	List<User> findAll() throws Exception;
}
