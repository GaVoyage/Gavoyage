package com.gavoyage.comment.service;

import java.sql.SQLException;
import java.util.List;

import com.gavoyage.comment.domain.Comment;
import com.gavoyage.comment.dto.request.CreateCommentReq;
import com.gavoyage.comment.dto.response.CommentRes;

public interface CommentService {
	
	void createComment(CreateCommentReq createCommentReq) throws SQLException;
	CommentRes findComment(Long commentIdx) throws SQLException;
	List<CommentRes> findAllComments() throws SQLException;
	void deleteComment(Long commentIdx) throws SQLException;
	List<CommentRes> findCommentsByReviewIdx(Long reviewIdx) throws SQLException; 
	
}
