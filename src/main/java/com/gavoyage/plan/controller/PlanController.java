package com.gavoyage.plan.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gavoyage.plan.domain.DailyPlan;
import com.gavoyage.plan.domain.Plan;
import com.gavoyage.plan.dto.request.PlanCreateDto;
import com.gavoyage.plan.dto.request.PlanCreateReq;
import com.gavoyage.plan.service.PlanServiceImpl;
import com.gavoyage.region.domain.AttractionInfo;
import com.gavoyage.region.service.RegionServiceImpl;
import com.gavoyage.user.domain.Users;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
//@CrossOrigin("*") @CrossOrigin은 인증이 필요 없는 요청은 처리 가능하지만 인증이 필요한 요청들은 cors가 거부 되어 시큐리티 필터에 등록하여야 한다. 
@RequestMapping("/plans")
public class PlanController {
	
	private final PlanServiceImpl planService;
	private final RegionServiceImpl regionService;
	
	@PostMapping("")
	public ResponseEntity<?> createPlan(@RequestBody() PlanCreateReq planCreateReq, HttpSession session) throws Exception{
		
		log.debug("createPlan()");
		log.debug(planCreateReq.toString());
		
//		User user = (User) session.getAttribute("user");
//		long userIdx = user.getUserIdx();
		
		planService.createPlan(planCreateReq);
		
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("")
	public ResponseEntity<List<Plan>> getPlans() throws Exception {
		
//		User user = (User) session.getAttribute("user");
//		long userIdx = user.getUserIdx();
		
		List<Plan> findPlans = planService.findPlans(1L);
		
		return new ResponseEntity<>(findPlans, HttpStatus.OK);
		
	}
	
	@GetMapping("/{planIdx}")
	public ResponseEntity<Map<LocalDate, List<AttractionInfo>>> getDailyPlans(@PathVariable Long planIdx) throws Exception {
		
//		User user = (User) session.getAttribute("user");
//		long userIdx = user.getUserIdx();
		
		Map<LocalDate, List<AttractionInfo>> attractionInfos = planService.findAllAttractionInfos(planIdx);
		
		return new ResponseEntity<>(attractionInfos, HttpStatus.OK);
	}
	
	@DeleteMapping("/{planIdx}")
	public ResponseEntity<?> deletePlan(@PathVariable Long planIdx){
		
		planService.deletePlan(planIdx);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/dailyplan/{dailyPlanIdx}")
	public ResponseEntity<?> deleteDailyPlan(@PathVariable Long dailyPlanIdx){
		
		planService.deleteDailyPlan(dailyPlanIdx);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
