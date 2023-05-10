package com.gavoyage.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gavoyage.user.domain.User;
import com.gavoyage.user.dto.request.UserJoinReq;
import com.gavoyage.user.dto.request.UserLoginReq;
import com.gavoyage.user.dto.response.UserLoginRes;

@Mapper
public interface UserMapper {
	
	int emailCheck(String email) throws SQLException;
	User login(UserLoginReq userLoginReq) throws SQLException;
	User findOne(Long userIdx) throws SQLException;
	List<User> findAll() throws SQLException;
	void join(UserJoinReq userJoinReq)  throws SQLException;

}



//package com.ssafy.enjoytrip.mapper;
//
//import java.sql.SQLException;
//import java.util.List;
//
//import org.apache.ibatis.annotations.Mapper;
//import com.ssafy.enjoytrip.model.MembersDto;
//
//@Mapper
//public interface MembersMapper {
//	MembersDto login(MembersDto mem) throws SQLException;
//
//	void joinMember(MembersDto memberDto) throws SQLException;
//
//	List<MembersDto> listUser() throws SQLException;
//
//	void memberdelete(String member_id)  throws SQLException;
//
//	MembersDto memberdetail(String member_id) throws SQLException;
//
//	void memberupdate(MembersDto mdto)  throws SQLException;
//	
//	MembersDto findId(String nickname) throws SQLException;
//
//	MembersDto findPassword(String nickname, String member_id) throws SQLException;
//}
