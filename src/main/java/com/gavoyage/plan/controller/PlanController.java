package com.gavoyage.plan.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.gavoyage.user.domain.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/plan")
public class PlanController {
	
	private final PlanServiceImpl planService;
	
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
	public ResponseEntity<List<Plan>> getPlans(){
		
//		User user = (User) session.getAttribute("user");
//		long userIdx = user.getUserIdx();
		
		List<Plan> findPlans = planService.findPlans(1L);
		
		return new ResponseEntity<>(findPlans, HttpStatus.OK);
		
	}
	
	@GetMapping("/dailyplan/{planIdx}")
	public ResponseEntity<List<DailyPlan>> getDailyPlans(@PathVariable Long planIdx){
		
//		User user = (User) session.getAttribute("user");
//		long userIdx = user.getUserIdx();
		
		List<DailyPlan> findDailyPlans = planService.findDailyPlans(planIdx);
		
		return new ResponseEntity<>(findDailyPlans, HttpStatus.OK);
	}
}
