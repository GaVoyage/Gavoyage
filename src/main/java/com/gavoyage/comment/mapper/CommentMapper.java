package com.gavoyage.comment.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.gavoyage.comment.dto.request.CreateCommentReq;
import com.gavoyage.comment.dto.response.CommentRes;

@Mapper
public interface CommentMapper {
	
	void createComment(CreateCommentReq createCommentReq);
	Optional<CommentRes> findComment(Long commentIdx);
	List<CommentRes> findAllComments();
	void deleteComment(Long commentIdx);
	List<CommentRes> findCommentsByReviewIdx(Long reviewIdx);
	
}
