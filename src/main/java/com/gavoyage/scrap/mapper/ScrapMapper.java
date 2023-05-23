package com.gavoyage.scrap.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.gavoyage.scrap.domain.Scrap;
import com.gavoyage.scrap.dto.ScrapDto;

@Mapper
public interface ScrapMapper {
	
	void createScrap(ScrapDto scrapDto);
	Scrap findScrapByContentId(ScrapDto scrapDto);
	int hasScrap(ScrapDto scrapDto);
	void scrap(Long scrapIdx);
	void unscrap(Long scrapIdx);
	
}
