package com.gavoyage.comment.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gavoyage.comment.domain.Comment;
import com.gavoyage.comment.dto.request.CreateCommentReq;
import com.gavoyage.comment.mapper.CommentMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
	
	private final CommentMapper commentMapper;

	@Override
	public void createComment(CreateCommentReq createCommentReq) throws SQLException {
		commentMapper.createComment(createCommentReq);
	}

	@Override
	public Comment findComment(Long commentIdx) throws SQLException {
		return commentMapper.findComment(commentIdx);
	}

	@Override
	public List<Comment> findAllComments() throws SQLException {
		return commentMapper.findAllComments();
	}

	@Override
	public void deleteComment(Long commentIdx) throws SQLException {
		commentMapper.deleteComment(commentIdx);
	}
	
	
	
}
