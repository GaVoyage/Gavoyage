package com.gavoyage.region.service;

import java.util.List;

import com.gavoyage.region.domain.AttractionDescription;
import com.gavoyage.region.domain.AttractionDetail;
import com.gavoyage.region.domain.AttractionInfo;
import com.gavoyage.region.domain.Gugun;
import com.gavoyage.region.domain.Sido;
import com.gavoyage.region.dto.request.RegionSearchReq;

public interface RegionService {
	
	List<Sido> getSido();
	List<Gugun> getGugun(int sidoCode);
	List<AttractionInfo> getAttractionInfos(RegionSearchReq regionSearchReq);
	AttractionInfo getAttractionInfosByContentId(int contentId);
	AttractionDescription getAttractionDescription(int contentId);
	AttractionDetail getAttractionDetail(int contentId);
	
}
