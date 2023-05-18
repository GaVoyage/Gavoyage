package com.gavoyage.plan.dto.response;

import com.gavoyage.plan.domain.Plan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPlansRes {
	private Plan plan;
	private int hasReview;
}
