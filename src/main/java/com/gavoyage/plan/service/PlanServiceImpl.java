package com.gavoyage.plan.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.gavoyage.mapper.PlanMapper;
import com.gavoyage.plan.domain.DailyPlan;
import com.gavoyage.plan.domain.Plan;
import com.gavoyage.plan.dto.request.DailyPlanCreateDto;
import com.gavoyage.plan.dto.request.PlanCreateReq;
import com.gavoyage.region.domain.AttractionInfo;
import com.gavoyage.region.service.RegionServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService{

	private final RegionServiceImpl regionService;
	private final PlanMapper planMapper;

	@Override
	public void createPlan(PlanCreateReq planCreateReq) throws Exception{
		
		planMapper.createPlan(planCreateReq);
		
		LocalDate curDate = planCreateReq.getStartDate();
		
		Long planIdx = planCreateReq.getPlanIdx();
		for(List<Integer> content_ids : planCreateReq.getDailyPlans()) {
			
			log.debug("curDate : " + curDate);
			
			for(Integer content_id : content_ids) {
				planMapper.createDailyPlan(new DailyPlanCreateDto(planIdx, content_id, curDate));
			}
			
			curDate = curDate.plusDays(1); // 날짜 증가
		}
	}

	@Override
	public List<Plan> findPlans(Long userIdx) throws Exception{
		return planMapper.getPlans(userIdx);
	}

	@Override
	public List<DailyPlan> findDailyPlans(Long planIdx) throws Exception{
		return planMapper.getDailyPlans(planIdx);
	}
	
	@Override
	public Map<LocalDate, List<AttractionInfo>> findAllAttractionInfos(Long planIdx) throws Exception{
		Map<LocalDate, List<AttractionInfo>> attractionInfos = new ConcurrentHashMap<>();
		
		for(DailyPlan dplan : findDailyPlans(planIdx)) {
			
			LocalDate dailyDate = dplan.getDailyDate();
			AttractionInfo info = regionService.getAttractionInfosByContentId(dplan.getContent_id());
			
			if(!attractionInfos.containsKey(dailyDate)) {
				attractionInfos.put(dailyDate, new ArrayList<>());
			}
			
			attractionInfos.get(dailyDate).add(info);
		
		}
		
		return attractionInfos; 
	}

}
