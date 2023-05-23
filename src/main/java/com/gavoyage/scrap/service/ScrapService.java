package com.gavoyage.scrap.service;

import java.util.List;

import com.gavoyage.scrap.domain.Scrap;
import com.gavoyage.scrap.dto.ScrapDto;
import com.gavoyage.scrap.dto.response.ScrapAttractionRes;

public interface ScrapService {
	Character pushScrap(ScrapDto scrapDto);
	Scrap findScrapByContentId(ScrapDto scrapDto);
	int hasScrap(ScrapDto scrapDto);
	List<ScrapAttractionRes> scrapAttractionByUserIdx(Long userIdx);
}
