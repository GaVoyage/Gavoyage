package com.gavoyage.comment.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRes {
	private Long commentIdx;
	private Long reviewIdx;
	private Long userIdx;
	private String nickname;
	private String userImageUrl;
	private String contents;
	public LocalDate createdAt;
}
