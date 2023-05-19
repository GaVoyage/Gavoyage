package com.gavoyage.review.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReviewReq {
	private Long userIdx;
	private Long planIdx;
	private Long reviewIdx;
	private String title;
	private List<Integer> recommendAttractions;
	private List<Integer> unRecommendAttractions;
	private String contents;
	private int hit;
}
