package com.gavoyage.follow.domain;

import com.gavoyage.config.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Follow extends BaseEntity{
	private Long followIdx;
	private Long userIdx;
	private Long targetUseIdx;
}
