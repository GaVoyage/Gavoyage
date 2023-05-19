package com.gavoyage.review.dto.sql;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateRecommendDto {
	private Long reviewIdx;
	private int content_id;
}
