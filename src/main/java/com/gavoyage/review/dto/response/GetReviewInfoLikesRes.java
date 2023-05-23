package com.gavoyage.review.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetReviewInfoLikesRes {
	private String nickname;
	private Long reviewIdx;
	private String title;
	private String contents;
	private LocalDate createdAt;
}
