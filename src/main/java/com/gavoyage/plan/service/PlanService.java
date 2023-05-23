package com.gavoyage.plan.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.gavoyage.plan.domain.DailyPlan;
import com.gavoyage.plan.dto.request.PlanCreateReq;
import com.gavoyage.plan.dto.response.GetPlansRes;
import com.gavoyage.region.domain.AttractionInfo;

public interface PlanService {
	
	void createPlan(PlanCreateReq planCreateReq);
	List<GetPlansRes> findPlans(Long userIdx);
	List<DailyPlan> findDailyPlans(Long planIdx);
	Map<LocalDate, List<AttractionInfo>> findAllAttractionInfos(Long planIdx);
	void deletePlan(Long planIdx);
	void deleteDailyPlan(Long dailyPlanIdx);
	
}