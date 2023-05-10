package com.gavoyage.plan.dto.request;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanCreateReq {
	private Long planIdx;
	private Long userIdx;
	private String title;
	private LocalDate startDate;
	private LocalDate endDate;
	private List<List<Integer>> dailyPlans;
	
}
