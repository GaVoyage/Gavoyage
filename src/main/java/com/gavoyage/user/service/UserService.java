package com.gavoyage.user.service;

import java.util.List;
import java.util.Optional;

import com.gavoyage.user.domain.Users;
import com.gavoyage.user.dto.request.UserJoinReq;
import com.gavoyage.user.dto.request.UserLoginReq;

public interface UserService {
	int emailCheck(String email) throws Exception;
	Users login(UserLoginReq userLoginReq) throws Exception;
	void join(UserJoinReq userJoinReq) throws Exception;
	Users findByUserIdx(Long userIdx) throws Exception;
	List<Users> findAll() throws Exception;
	void deleteUser(Long userIdx) throws Exception;
	Optional<Users> findByUserEmail(String email) throws Exception;
	void updateRefreshToken(String email, String refreshToken) throws Exception;	
	Users findByRefreshToken(String refreshToken) throws Exception;
}
