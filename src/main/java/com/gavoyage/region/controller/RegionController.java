package com.gavoyage.region.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController()
@RequiredArgsConstructor
@RequestMapping("/regions")
public class RegionController {
	
	private final RegionService regionService;
	
	@PostMapping("/search")
	public ResponseEntity<List<AttractionInfo>> search(@RequestBody RegionSearchReq regionSearchReq, HttpServletRequest request,  HttpServletResponse response) throws Exception {
		List<AttractionInfo> result = regionService.getAttractionInfos(regionSearchReq);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/sido")
	public ResponseEntity<List<Sido>> getSido() throws Exception {
		List<Sido> result = regionService.getSido();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/gugun")
	public ResponseEntity<List<Gugun>> getSiGugun(int sidoCode) throws Exception {
		List<Gugun> result = regionService.getGugun(sidoCode);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/detail")
	public ResponseEntity<AttractionDetail> getAttractionDetail(int contentId) throws Exception {
		AttractionDetail result = regionService.getAttractionDetail(contentId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/description")
	public ResponseEntity<AttractionDescription> getAttractionDescription(int contentId) throws Exception {
		AttractionDescription result = regionService.getAttractionDescription(contentId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/info")
	public ResponseEntity<AttractionInfo> getAttractionInfo(int contentId) throws Exception {
		AttractionInfo result = regionService.getAttractionInfosByContentId(contentId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
		
}
