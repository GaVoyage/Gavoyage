package com.gavoyage.scrap.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gavoyage.exception.RestException;
import com.gavoyage.exception.errorcode.ErrorCode;
import com.gavoyage.scrap.domain.Scrap;
import com.gavoyage.scrap.dto.ScrapDto;
import com.gavoyage.scrap.dto.response.ScrapAttractionRes;
import com.gavoyage.scrap.mapper.ScrapMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ScrapServiceImpl implements ScrapService{
	
	private final ScrapMapper scrapMapper;
	
	@Override
	public Character pushScrap(ScrapDto scrapDto) {
		
		// 처음 스크랩한 경우
		if(hasScrap(scrapDto) == 0) {
			scrapMapper.createScrap(scrapDto);	
			return 'Y';
		}
		
		Scrap scrap = scrapMapper.findScrapByContentId(scrapDto).orElseThrow(() -> new RestException(ErrorCode.RESOURCE_NOT_FOUND));
		
		if(scrap.getStatus() == 'Y') { // 좋아요가 이미 눌린 경우
			scrapMapper.unscrap(scrap.getScrapIdx());
			return 'N';
		}
		
		if(scrap.getStatus() == 'N') { // 좋아요가 눌러저 있지 않은 경우
			scrapMapper.scrap(scrap.getScrapIdx());
			return 'Y';
		}
		
		return null;
	}

	@Override
	public Optional<Scrap> findScrapByContentId(ScrapDto scrapDto) {
		return scrapMapper.findScrapByContentId(scrapDto);
	}

	@Override
	public int hasScrap(ScrapDto scrapDto) {
		return scrapMapper.hasScrap(scrapDto);
	}

	@Override
	public List<ScrapAttractionRes> scrapAttractionByUserIdx(Long userIdx) {
		return scrapMapper.scrapAttractionByUserIdx(userIdx);
	}

}
