package com.gavoyage.plan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gavoyage.plan.domain.DailyPlan;
import com.gavoyage.plan.domain.Plan;
import com.gavoyage.plan.dto.request.DailyPlanCreateDto;
import com.gavoyage.plan.dto.request.PlanCreateReq;

@Mapper
public interface PlanMapper {
	
	void createPlan(PlanCreateReq planCreateReq);
	void createDailyPlan(DailyPlanCreateDto dailyPlanCreateDto);
	List<Plan> getPlans(Long userIdx);
	List<DailyPlan> getDailyPlans(Long planIdx);
	void deletePlan(Long planIdx);
	void deleteDailyPlan(Long dailyPlanIdx);
	
}
