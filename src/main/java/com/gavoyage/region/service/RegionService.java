package com.gavoyage.region.service;

import java.util.List;

import com.gavoyage.region.domain.AttractionDescription;
import com.gavoyage.region.domain.AttractionDetail;
import com.gavoyage.region.domain.AttractionInfo;
import com.gavoyage.region.domain.Gugun;
import com.gavoyage.region.domain.Sido;
import com.gavoyage.region.dto.request.RegionSearchReq;

public interface RegionService {
	
	List<Sido> getSido() throws Exception;
	List<Gugun> getGugun(int sidoCode) throws Exception;
	List<AttractionInfo> getAttractionInfos(RegionSearchReq regionSearchReq) throws Exception;
	AttractionInfo getAttractionInfosByContentId(int contentId) throws Exception;
	AttractionDescription getAttractionDescription(int contentId) throws Exception;
	AttractionDetail getAttractionDetail(int contentId) throws Exception;
	
}
