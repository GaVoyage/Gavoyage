package com.gavoyage.region.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gavoyage.region.domain.AttractionDescription;
import com.gavoyage.region.domain.AttractionDetail;
import com.gavoyage.region.domain.AttractionInfo;
import com.gavoyage.region.domain.Gugun;
import com.gavoyage.region.domain.Sido;
import com.gavoyage.region.service.RegionService;

import lombok.RequiredArgsConstructor;

@RestController()
@RequiredArgsConstructor
@RequestMapping("/region")
public class RegionController {
	
	private final RegionService regionService;
	
	@GetMapping("/search")
	public List<AttractionInfo> search(Locale locale, int sido, int gugun, int content_type_id, HttpServletRequest request,  HttpServletResponse response) throws Exception {
		List<AttractionInfo> result = regionService.getAttractionInfos(new AttractionInfo(sido, gugun, content_type_id));
		return result;
	}
	
	@GetMapping("/sido")
	public List<Sido> getSido() throws Exception {
		List<Sido> result = regionService.getSido();
		return result;
	}
	
	@GetMapping("/gugun")
	public List<Gugun> getGugun(int sidoCode) throws Exception {
		List<Gugun> result = regionService.getSigungu(sidoCode);
		return result;
	}
	
	@GetMapping("/detail")
	public AttractionDetail getDetail(int contentId) throws Exception {
		AttractionDetail result = regionService.getAttractionDetail(contentId);
		return result;
	}
	
	@GetMapping("/description")
	public AttractionDescription getDescription(int contentId) throws Exception {
		AttractionDescription result = regionService.getAttractionDescription(contentId);
		return result;
	}
	
	@GetMapping("/info")
	public List<AttractionInfo> getInfo(AttractionInfo attr) throws Exception {
		List<AttractionInfo> result = regionService.getAttractionInfos(attr);
		return result;
	}
	
	
	
}
