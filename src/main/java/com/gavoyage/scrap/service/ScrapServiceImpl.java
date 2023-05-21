package com.gavoyage.scrap.service;

import org.springframework.stereotype.Service;

import com.gavoyage.scrap.domain.Scrap;
import com.gavoyage.scrap.dto.ScrapDto;
import com.gavoyage.scrap.mapper.ScrapMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScrapServiceImpl implements ScrapService{
	
	private final ScrapMapper scrapMapper;
	
	@Override
	public void pushScrap(ScrapDto scrapDto) throws Exception {
		
		// 처음 스크랩한 경우
		if(hasScrap(scrapDto) == 0) {
			scrapMapper.createScrap(scrapDto);	
			return;
		}
		
		Scrap scrap = scrapMapper.findScrapByContentId(scrapDto);
		
		if(scrap.getStatus() == 'Y') { // 좋아요가 이미 눌린 경우
			scrapMapper.unscrap(scrap.getScrapIdx());
			return;
		}
		
		if(scrap.getStatus() == 'N') { // 좋아요가 눌러저 있지 않은 경우
			scrapMapper.scrap(scrap.getScrapIdx());
		}
	}

	@Override
	public Scrap findScrapByContentId(ScrapDto scrapDto) throws Exception {
		return scrapMapper.findScrapByContentId(scrapDto);
	}

	@Override
	public int hasScrap(ScrapDto scrapDto) throws Exception {
		return scrapMapper.hasScrap(scrapDto);
	}

}
