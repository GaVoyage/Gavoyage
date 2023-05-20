package com.gavoyage.review.domain;

import com.gavoyage.config.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review extends BaseEntity{
	private String reviewIdx;
	private Long userIdx;
	private Long planIdx;
	private String title;
	private String contents;
	private Integer hit;
}
