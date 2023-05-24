package com.gavoyage.comment.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gavoyage.comment.domain.Comment;
import com.gavoyage.comment.dto.request.CreateCommentReq;
import com.gavoyage.comment.dto.response.CommentRes;
import com.gavoyage.comment.service.CommentServiceImpl;
import com.gavoyage.config.login.PrincipalDetails;
import com.gavoyage.exception.RestException;
import com.gavoyage.exception.errorcode.ErrorCode;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
	
	private final CommentServiceImpl commentService;
	
	@PostMapping
	public ResponseEntity<Void> createComment(@RequestBody CreateCommentReq createCommentReq, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		createCommentReq.setUserIdx(principalDetails.getUserIdx());
		commentService.createComment(createCommentReq);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<CommentRes>> findAllComments() {
		return new ResponseEntity<>(commentService.findAllComments(), HttpStatus.OK);
	}
	
	@DeleteMapping("/{commentIdx}")
	public ResponseEntity<List<Comment>> deleteComment(@PathVariable Long commentIdx) {
		commentService.deleteComment(commentIdx);
		return new ResponseEntity<>(HttpStatus.OK);
	}
		
	@GetMapping("/by-reviewIdx/{reviewIdx}")
	public ResponseEntity<List<CommentRes>> findCommentsByReviewIdx(@PathVariable Long reviewIdx) {
		return new ResponseEntity<>(commentService.findCommentsByReviewIdx(reviewIdx), HttpStatus.OK); 
	}
	
}
