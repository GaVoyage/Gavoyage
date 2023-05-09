package com.gavoyage.plan.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gavoyage.mapper.PlanMapper;
import com.gavoyage.plan.dto.request.DailyPlanCreateDto;
import com.gavoyage.plan.dto.request.PlanCreateReq;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService{

	private final PlanMapper planMapper;

	@Override
	public void createPlan(PlanCreateReq planCreateReq) {
		
		planMapper.createPlan(planCreateReq);
		
		LocalDate curDate = planCreateReq.getStartDate();
		Long userIdx = planCreateReq.getUserIdx();
		Long planIdx = planCreateReq.getPlanIdx();
		
		for(List<Integer> content_ids : planCreateReq.getDailyPlans()) {
			
			for(Integer content_id : content_ids) {
				planMapper.createDailyPlan(new DailyPlanCreateDto(planIdx, content_id, curDate));
			}
			
		}
		
		
	}
	
	

}
