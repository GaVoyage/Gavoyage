package com.gavoyage.region.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gavoyage.mapper.RegionMapper;
import com.gavoyage.region.domain.AttractionDescription;
import com.gavoyage.region.domain.AttractionDetail;
import com.gavoyage.region.domain.AttractionInfo;
import com.gavoyage.region.domain.Gugun;
import com.gavoyage.region.domain.Sido;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {
	
	
	private final RegionMapper regionMapper;

	@Override
	public List<Sido> getSido() throws Exception {
		
		return regionMapper.getSido();
	}

	@Override
	public List<Gugun> getSigungu(int sidoCode) throws Exception{

		return regionMapper.getSigungu(sidoCode);
	}

	@Override
	public List<AttractionInfo> getAttractionInfos(AttractionInfo attr) throws Exception {

		return regionMapper.getAttractionInfos(attr);
	}

	@Override
	public AttractionDescription getAttractionDescription(int contentId) throws Exception {

		return regionMapper.getAttractionDescription(contentId);
	}

	@Override
	public AttractionDetail getAttractionDetail(int contentId) throws Exception {

		return regionMapper.getAttractionDetail(contentId);
	}

}
