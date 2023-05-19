package com.gavoyage.review.dto.sql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindReviewInfo {
	private Long reviewIdx;
	private String title;
	private String contents;
	private int hit;
}
