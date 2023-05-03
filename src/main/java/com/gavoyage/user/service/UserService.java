package com.gavoyage.user.service;

import java.sql.SQLException;

import com.gavoyage.user.domain.User;
import com.gavoyage.user.dto.request.UserLoginReq;

public interface UserService {
	User findByEmail(String email) throws SQLException;
}
