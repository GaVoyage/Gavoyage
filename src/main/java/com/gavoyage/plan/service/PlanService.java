package com.gavoyage.plan.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.gavoyage.plan.domain.DailyPlan;
import com.gavoyage.plan.domain.Plan;
import com.gavoyage.plan.dto.request.PlanCreateReq;
import com.gavoyage.region.domain.AttractionInfo;

public interface PlanService {
	
	void createPlan(PlanCreateReq planCreateReq) throws Exception;
	List<Plan> findPlans(Long userIdx) throws Exception;
	List<DailyPlan> findDailyPlans(Long planIdx) throws Exception;
	Map<LocalDate, List<AttractionInfo>> findAllAttractionInfos(Long planIdx) throws Exception;
	void deletePlan(Long planIdx);
	void deleteDailyPlan(Long dailyPlanIdx);
	
}