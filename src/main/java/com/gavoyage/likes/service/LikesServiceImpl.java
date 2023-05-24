package com.gavoyage.likes.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gavoyage.exception.RestException;
import com.gavoyage.exception.errorcode.ErrorCode;
import com.gavoyage.likes.domain.Likes;
import com.gavoyage.likes.mapper.LikesMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class LikesServiceImpl implements LikesService{
	
	private final LikesMapper likesMapper;
	
	@Override
	public Character pushLikes(Long reviewIdx, Long userIdx){
		
		log.debug("pushlikes in service");
		
		// 처음 좋아요를 누른 경우
		if(hasLikes(reviewIdx, userIdx) == 0) {
			likesMapper.createLikes(reviewIdx, userIdx);	
			return 'Y';
		}
		
		Likes likes = findLikesByReviewIdx(reviewIdx, userIdx).orElseThrow(() -> new RestException(ErrorCode.RESOURCE_NOT_FOUND));
		
		if(likes.getStatus() == 'Y') { // 좋아요가 이미 눌린 경우
			likesMapper.dislike(likes.getLikeIdx());
			return 'N';
		}
		
		if(likes.getStatus() == 'N') { // 좋아요가 눌러저 있지 않은 경우
			likesMapper.like(likes.getLikeIdx());
			return 'Y';
		}
		
		return null;

	}

	@Override
	public Optional<Likes> findLikesByReviewIdx(Long reviewIdx, Long userIdx) {
		return likesMapper.findLikesByReviewIdx(reviewIdx, userIdx);
	}

	@Override
	public int hasLikes(Long reviewIdx, Long userIdx) {
		return likesMapper.hasLikes(reviewIdx, userIdx);
	}
	

}
