package com.gavoyage.comment.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gavoyage.comment.domain.Comment;
import com.gavoyage.comment.dto.request.CreateCommentReq;

@Mapper
public interface CommentMapper {
	
	void createComment(CreateCommentReq createCommentReq) throws SQLException;
	Comment findComment(Long commentIdx) throws SQLException;
	List<Comment> findAllComments() throws SQLException;
	void deleteComment(Long commentIdx) throws SQLException;
	
}
