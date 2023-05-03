package com.gavoyage.user.service;

import java.sql.SQLException;

import com.gavoyage.user.domain.User;
import com.gavoyage.user.dto.request.UserLoginReq;
import com.gavoyage.user.dto.response.UserLoginRes;

public interface UserService {
	int emailCheck(String email) throws Exception;
	UserLoginRes login(UserLoginReq userLoginReq) throws SQLException;
	User findOne(Long userIdx) throws SQLException;
}
