package com.gavoyage.plan.domain;

import java.time.LocalDate;

import com.gavoyage.config.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyPlan extends BaseEntity{
	private Long dailyPlanIdx;
	private Long planIdx;
	private Integer content_id;
	private LocalDate dailyDate;
}
