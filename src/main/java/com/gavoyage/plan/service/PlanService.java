package com.gavoyage.plan.service;

import java.util.List;

import com.gavoyage.plan.domain.DailyPlan;
import com.gavoyage.plan.domain.Plan;
import com.gavoyage.plan.dto.request.PlanCreateReq;

public interface PlanService {
	
	void createPlan(PlanCreateReq planCreateReq);
	List<Plan> findPlans(Long userIdx);
	List<DailyPlan> findDailyPlans(Long planIdx);
	
}