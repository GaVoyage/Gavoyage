package com.gavoyage.scrap.service;

import com.gavoyage.scrap.domain.Scrap;
import com.gavoyage.scrap.dto.ScrapDto;

public interface ScrapService {
	void pushScrap(ScrapDto scrapDto) throws Exception;
	Scrap findScrapByContentId(ScrapDto scrapDto) throws Exception;
	int hasScrap(ScrapDto scrapDto) throws Exception;
}
