package com.gavoyage.comment.service;

import java.util.List;

import com.gavoyage.comment.dto.request.CreateCommentReq;
import com.gavoyage.comment.dto.response.CommentRes;

public interface CommentService {
	
	void createComment(CreateCommentReq createCommentReq);
	CommentRes findComment(Long commentIdx);
	List<CommentRes> findAllComments();
	void deleteComment(Long commentIdx);
	List<CommentRes> findCommentsByReviewIdx(Long reviewIdx); 
	
}
