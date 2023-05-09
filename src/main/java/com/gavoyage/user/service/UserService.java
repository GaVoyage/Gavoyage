package com.gavoyage.user.service;

import java.sql.SQLException;
import java.util.List;

import com.gavoyage.user.domain.User;
import com.gavoyage.user.dto.request.UserLoginReq;

public interface UserService {
	int emailCheck(String email) throws Exception;
	User login(UserLoginReq userLoginReq) throws SQLException;
	User findOne(Long userIdx) throws SQLException;
	List<User> findAll() throws SQLException;
}