package com.gavoyage.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gavoyage.comment.dto.request.CreateCommentReq;
import com.gavoyage.comment.dto.response.CommentRes;

@Mapper
public interface CommentMapper {
	
	void createComment(CreateCommentReq createCommentReq);
	CommentRes findComment(Long commentIdx);
	CommentRes findByReveiwIdx(Long reviewIdx);
	List<CommentRes> findAllComments();
	void deleteComment(Long commentIdx);
	List<CommentRes> findCommentsByReviewIdx(Long reviewIdx);
	
}
