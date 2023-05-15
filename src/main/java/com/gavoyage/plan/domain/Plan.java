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
public class Plan extends BaseEntity {
	private Long planIdx;
	private Long userIdx;
	private String title;
	private LocalDate startDate;
	private LocalDate endDate;
}
