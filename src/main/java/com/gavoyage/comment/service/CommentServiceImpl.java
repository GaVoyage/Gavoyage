package com.gavoyage.comment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gavoyage.comment.dto.request.CreateCommentReq;
import com.gavoyage.comment.dto.response.CommentRes;
import com.gavoyage.comment.mapper.CommentMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService{
	
	private final CommentMapper commentMapper;
	
	@Override
	public void createComment(CreateCommentReq createCommentReq) {
		commentMapper.createComment(createCommentReq);
	}

	@Override
	public Optional<CommentRes> findComment(Long commentIdx) {
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
		System.out.println(commentMapper.findCommentsByReviewIdx(reviewIdx));
		return commentMapper.findCommentsByReviewIdx(reviewIdx);
	}
	
}
