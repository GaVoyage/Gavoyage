package com.gavoyage.scrap.service;

import java.util.List;
import java.util.Optional;

import com.gavoyage.scrap.domain.Scrap;
import com.gavoyage.scrap.dto.ScrapDto;
import com.gavoyage.scrap.dto.response.ScrapAttractionRes;

public interface ScrapService {
	Character pushScrap(ScrapDto scrapDto);
	Optional<Scrap> findScrapByContentId(ScrapDto scrapDto);
	int hasScrap(ScrapDto scrapDto);
	List<ScrapAttractionRes> scrapAttractionByUserIdx(Long userIdx);
}
