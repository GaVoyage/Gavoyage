package com.gavoyage.plan.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gavoyage.plan.domain.DailyPlan;
import com.gavoyage.plan.domain.Plan;
import com.gavoyage.plan.dto.request.DailyPlanCreateDto;
import com.gavoyage.plan.dto.request.PlanCreateReq;
import com.gavoyage.plan.dto.response.GetPlansRes;
import com.gavoyage.plan.mapper.PlanMapper;
import com.gavoyage.region.domain.AttractionInfo;
import com.gavoyage.region.service.RegionServiceImpl;
import com.gavoyage.review.service.ReviewServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PlanServiceImpl implements PlanService{

	private final RegionServiceImpl regionService;
	private final ReviewServiceImpl reviewSevice;
	private final PlanMapper planMapper;
	
	@Override
	@Transactional
	public void createPlan(PlanCreateReq planCreateReq) {
		
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
	public List<GetPlansRes> findPlans(Long userIdx) {
		
		List<Plan> plans = planMapper.getPlans(userIdx);
		List<GetPlansRes> getPlansReses = new ArrayList<GetPlansRes>();
		
		for(Plan plan : plans) {
			getPlansReses.add(new GetPlansRes(plan, reviewSevice.hasReview(plan.getPlanIdx())));
		}
	
		return getPlansReses;
	}

	@Override
	public List<DailyPlan> findDailyPlans(Long planIdx) {
		return planMapper.getDailyPlans(planIdx);
	}
	
	@Override
	public Map<LocalDate, List<AttractionInfo>> findAllAttractionInfos(Long planIdx) {
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

	@Override
	public void deletePlan(Long planIdx) {
		planMapper.deletePlan(planIdx);
		for(DailyPlan dailyPlans : findDailyPlans(planIdx)) {
			deleteDailyPlan(dailyPlans.getDailyPlanIdx());
		}
	}

	@Override
	public void deleteDailyPlan(Long dailyPlanIdx) {
		planMapper.deleteDailyPlan(dailyPlanIdx);
	}

}
