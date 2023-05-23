package com.gavoyage.scrap.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gavoyage.scrap.domain.Scrap;
import com.gavoyage.scrap.dto.ScrapDto;
import com.gavoyage.scrap.dto.response.ScrapAttractionRes;

@Mapper
public interface ScrapMapper {
	
	void createScrap(ScrapDto scrapDto);
	Scrap findScrapByContentId(ScrapDto scrapDto);
	int hasScrap(ScrapDto scrapDto);
	void scrap(Long scrapIdx);
	void unscrap(Long scrapIdx);
	List<ScrapAttractionRes> scrapAttractionByUserIdx(Long userIdx);
	
}
