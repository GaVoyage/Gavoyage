package com.gavoyage.region.service;

import java.util.List;

import com.gavoyage.region.domain.AttractionDescription;
import com.gavoyage.region.domain.AttractionDetail;
import com.gavoyage.region.domain.AttractionInfo;
import com.gavoyage.region.domain.Gugun;
import com.gavoyage.region.domain.Sido;

public interface RegionService {

	List<Sido> getSido() throws Exception;
	List<Gugun> getSigungu(int sidoCode) throws Exception;
	List<AttractionInfo> getAttractionInfos(AttractionInfo attr) throws Exception;
	AttractionDescription getAttractionDescription(int contentId) throws Exception;
	AttractionDetail getAttractionDetail(int contentId) throws Exception;
}
