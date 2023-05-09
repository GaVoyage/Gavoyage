package com.gavoyage.plan.dto.request;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlanCreateDto {
	private Long userIdx;
	private String title;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	
}
