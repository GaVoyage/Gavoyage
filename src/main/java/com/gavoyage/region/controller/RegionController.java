package com.gavoyage.region.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gavoyage.region.domain.AttractionDescription;
import com.gavoyage.region.domain.AttractionDetail;
import com.gavoyage.region.domain.AttractionInfo;
import com.gavoyage.region.domain.Gugun;
import com.gavoyage.region.domain.Sido;
import com.gavoyage.region.dto.request.RegionSearchReq;
import com.gavoyage.region.service.RegionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController()
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/regions")
public class RegionController {
	
	private final RegionService regionService;
	
	@PostMapping("/search")
	public List<AttractionInfo> search(@RequestBody RegionSearchReq regionSearchReq, HttpServletRequest request,  HttpServletResponse response) throws Exception {
		log.debug("RegionSearchReq : " + regionSearchReq);
		List<AttractionInfo> result = regionService.getAttractionInfos(regionSearchReq);
		return result;
	}
	
	@GetMapping("/sido")
	public List<Sido> getSido() throws Exception {
		List<Sido> result = regionService.getSido();
		return result;
	}
	
	@GetMapping("/gugun")
	public List<Gugun> getSiGugun(int sidoCode) throws Exception {
		List<Gugun> result = regionService.getGugun(sidoCode);
		return result;
	}
	
	@GetMapping("/detail")
	public AttractionDetail getAttractionDetail(int contentId) throws Exception {
		AttractionDetail result = regionService.getAttractionDetail(contentId);
		return result;
	}
	
	@GetMapping("/description")
	public AttractionDescription getAttractionDescription(int contentId) throws Exception {
		AttractionDescription result = regionService.getAttractionDescription(contentId);
		return result;
	}
	
	@GetMapping("/info")
	public AttractionInfo getAttractionInfo(int contentId) throws Exception {
		AttractionInfo result = regionService.getAttractionInfosByContentId(contentId);
		return result;
	}
		
}
