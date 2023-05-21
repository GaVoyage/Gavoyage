package com.gavoyage.likes.domain;

import com.gavoyage.config.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Likes extends BaseEntity{
	private Long likeIdx;
	private Long userIdx;
	private Long reviewIdx;
}
