package com.gavoyage.comment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentReq {
	private Long reviewIdx;
	private Long userIdx;
	private String contents;
}
