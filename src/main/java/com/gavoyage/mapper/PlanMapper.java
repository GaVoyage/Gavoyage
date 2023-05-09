package com.gavoyage.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gavoyage.plan.dto.request.DailyPlanCreateDto;
import com.gavoyage.plan.dto.request.PlanCreateReq;

@Mapper
public interface PlanMapper {
	
	void createPlan(PlanCreateReq planCreateReq);
	void createDailyPlan(DailyPlanCreateDto dailyPlanCreateDto);
	
}
