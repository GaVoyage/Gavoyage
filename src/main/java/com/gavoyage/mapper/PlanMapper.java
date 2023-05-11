package com.gavoyage.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gavoyage.plan.domain.DailyPlan;
import com.gavoyage.plan.domain.Plan;
import com.gavoyage.plan.dto.request.DailyPlanCreateDto;
import com.gavoyage.plan.dto.request.PlanCreateReq;

@Mapper
public interface PlanMapper {
	
	void createPlan(PlanCreateReq planCreateReq) throws SQLException;
	void createDailyPlan(DailyPlanCreateDto dailyPlanCreateDto) throws SQLException;
	List<Plan> getPlans(Long userIdx) throws SQLException;
	List<DailyPlan> getDailyPlans(Long planIdx) throws SQLException;
	void deletePlan(Long planIdx);
	void deleteDailyPlan(Long dailyPlanIdx);
	
}
