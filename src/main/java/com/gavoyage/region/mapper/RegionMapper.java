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
	List<Sido> getSido() throws SQLException;
	List<Gugun> getGugun(int sidoCode) throws SQLException;
	
	AttractionDescription getAttractionDescription(int contentId) throws SQLException;
	AttractionDetail getAttractionDetail(int contentId) throws SQLException;
	
	List<AttractionInfo> getAttractionInfos(RegionSearchReq regionSearchReq) throws SQLException;
	AttractionInfo getAttractionInfosByContentId(int contentId) throws SQLException;
}