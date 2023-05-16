package com.gavoyage.review.service;

import org.springframework.stereotype.Service;
import com.gavoyage.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
	
	private final ReviewMapper reviewMapper;
	
	@Override
	public int hasReview(Long planIdx) throws Exception {
		return reviewMapper.hasReview(planIdx);
	}
	
}
