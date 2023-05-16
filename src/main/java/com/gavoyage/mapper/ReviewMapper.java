package com.gavoyage.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gavoyage.plan.domain.DailyPlan;
import com.gavoyage.plan.domain.Plan;
import com.gavoyage.plan.dto.request.DailyPlanCreateDto;
import com.gavoyage.plan.dto.request.PlanCreateReq;

@Mapper
public interface ReviewMapper {
	
	int hasReview(Long planIdx) throws SQLException; 
	
}
