package com.gavoyage.comment.service;

import java.sql.SQLException;
import java.util.List;

import com.gavoyage.comment.domain.Comment;
import com.gavoyage.comment.dto.request.CreateCommentReq;

public interface CommentService {
	
	void createComment(CreateCommentReq createCommentReq) throws SQLException;
	Comment findComment(Long commentIdx) throws SQLException;
	List<Comment> findAllComments() throws SQLException;
	void deleteComment(Long commentIdx) throws SQLException;
	
}
