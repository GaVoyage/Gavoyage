package com.gavoyage.scrap.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.gavoyage.scrap.domain.Scrap;
import com.gavoyage.scrap.dto.ScrapDto;

@Mapper
public interface ScrapMapper {
	
	void createScrap(ScrapDto scrapDto) throws SQLException;
	Scrap findScrapByContentId(ScrapDto scrapDto) throws SQLException;
	int hasScrap(ScrapDto scrapDto) throws SQLException;
	void scrap(Long scrapIdx) throws SQLException;;
	void unscrap(Long scrapIdx) throws SQLException;;
	
}
