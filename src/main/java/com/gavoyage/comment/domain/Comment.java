package com.gavoyage.comment.domain;

import com.gavoyage.config.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity{
	private Long commentIdx;
	private Long userIdx;
	private Long reviewIdx;
	private String contents;
}
