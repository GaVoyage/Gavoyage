package com.gavoyage.plan.dto.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyPlanCreateDto {
	private Long planIdx;
	private Integer content_id;
	private LocalDate dailyDate;
}
