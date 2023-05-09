package com.gavoyage.plan.dto.request;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class PlanCreateReq {
	private Long planIdx;
	private Long userIdx;
	private String title;
	private LocalDate startDate;
	private LocalDate endDate;
	private List<List<Integer>> dailyPlans;
	
}
