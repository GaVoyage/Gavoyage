package com.gavoyage.plan.dto.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanCreateDto {
	private Long userIdx;
	private String title;
	private LocalDate startDate;
	private LocalDate endDate;
	
}
