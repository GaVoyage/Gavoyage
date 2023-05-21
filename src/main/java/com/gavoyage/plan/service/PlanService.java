package com.gavoyage.plan.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.gavoyage.plan.domain.DailyPlan;
import com.gavoyage.plan.dto.request.PlanCreateReq;
import com.gavoyage.plan.dto.response.GetPlansRes;
import com.gavoyage.region.domain.AttractionInfo;

public interface PlanService {
	
	void createPlan(PlanCreateReq planCreateReq) throws Exception;
	List<GetPlansRes> findPlans(Long userIdx) throws Exception;
	List<DailyPlan> findDailyPlans(Long planIdx) throws Exception;
	Map<LocalDate, List<AttractionInfo>> findAllAttractionInfos(Long planIdx) throws Exception;
	void deletePlan(Long planIdx) throws Exception;
	void deleteDailyPlan(Long dailyPlanIdx) throws Exception;
	
}