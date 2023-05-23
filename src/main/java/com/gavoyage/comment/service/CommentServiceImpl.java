package com.gavoyage.comment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gavoyage.comment.dto.request.CreateCommentReq;
import com.gavoyage.comment.dto.response.CommentRes;
import com.gavoyage.comment.mapper.CommentMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
	
	private final CommentMapper commentMapper;

	@Override
	public void createComment(CreateCommentReq createCommentReq) {
		commentMapper.createComment(createCommentReq);
	}

	@Override
	public CommentRes findComment(Long commentIdx) {
		return commentMapper.findComment(commentIdx);
	}

	@Override
	public List<CommentRes> findAllComments() {
		return commentMapper.findAllComments();
	}

	@Override
	public void deleteComment(Long commentIdx) {
		commentMapper.deleteComment(commentIdx);
	}

	@Override
	public List<CommentRes> findCommentsByReviewIdx(Long reviewIdx) {
		return commentMapper.findCommentsByReviewIdx(reviewIdx);
	}
	
}
