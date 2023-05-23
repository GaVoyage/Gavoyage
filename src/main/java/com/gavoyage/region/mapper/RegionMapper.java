package com.gavoyage.region.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.gavoyage.region.domain.AttractionDescription;
import com.gavoyage.region.domain.AttractionDetail;
import com.gavoyage.region.domain.AttractionInfo;
import com.gavoyage.region.domain.Gugun;
import com.gavoyage.region.domain.Sido;
import com.gavoyage.region.dto.request.RegionSearchReq;

@Mapper
public interface RegionMapper {
	List<Sido> getSido();
	List<Gugun> getGugun(int sidoCode);
	
	AttractionDescription getAttractionDescription(int contentId);
	AttractionDetail getAttractionDetail(int contentId);
	
	List<AttractionInfo> getAttractionInfos(RegionSearchReq regionSearchReq);
	AttractionInfo getAttractionInfosByContentId(int contentId);
}