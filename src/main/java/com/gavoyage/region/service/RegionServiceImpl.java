package com.gavoyage.region.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gavoyage.region.domain.AttractionDescription;
import com.gavoyage.region.domain.AttractionDetail;
import com.gavoyage.region.domain.AttractionInfo;
import com.gavoyage.region.domain.Gugun;
import com.gavoyage.region.domain.Sido;
import com.gavoyage.region.dto.request.RegionSearchReq;
import com.gavoyage.region.mapper.RegionMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {
	
	
	private final RegionMapper regionMapper;

	@Override
	public List<Sido> getSido() {
		
		return regionMapper.getSido();
	}

	@Override
	public List<Gugun> getGugun(int sidoCode) {
		return regionMapper.getGugun(sidoCode);
	}

	@Override
	public AttractionDescription getAttractionDescription(int contentId) {

		return regionMapper.getAttractionDescription(contentId);
	}

	@Override
	public AttractionDetail getAttractionDetail(int contentId) {

		return regionMapper.getAttractionDetail(contentId);
	}

	@Override
	public List<AttractionInfo> getAttractionInfos(RegionSearchReq regionSearchReq) {
		return regionMapper.getAttractionInfos(regionSearchReq);
	}

	@Override
	public AttractionInfo getAttractionInfosByContentId(int contentId) {
		return regionMapper.getAttractionInfosByContentId(contentId);
	}

}
