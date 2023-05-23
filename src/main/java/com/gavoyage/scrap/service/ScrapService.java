package com.gavoyage.scrap.service;

import com.gavoyage.scrap.domain.Scrap;
import com.gavoyage.scrap.dto.ScrapDto;

public interface ScrapService {
	Character pushScrap(ScrapDto scrapDto);
	Scrap findScrapByContentId(ScrapDto scrapDto);
	int hasScrap(ScrapDto scrapDto);
}
