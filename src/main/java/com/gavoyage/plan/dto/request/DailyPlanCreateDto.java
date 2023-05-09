package com.gavoyage.plan.dto.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DailyPlanCreateDto {
	private Long planIdx;
	private Integer content_id;
	private LocalDate dailyDate;
}
