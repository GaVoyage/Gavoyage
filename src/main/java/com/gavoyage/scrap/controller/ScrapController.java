package com.gavoyage.scrap.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gavoyage.config.login.PrincipalDetails;
import com.gavoyage.scrap.dto.ScrapDto;
import com.gavoyage.scrap.service.ScrapServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scraps")
public class ScrapController {
	
	private final ScrapServiceImpl scrapService;
	
	@PostMapping("/{content_id}")
	public ResponseEntity<Character> scrapAttraction(@PathVariable Integer content_id, @AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception{
		
		return new ResponseEntity<>(scrapService.pushScrap(ScrapDto.builder().
				userIdx(principalDetails.getUserIdx())
				.content_id(content_id)
				.build()), HttpStatus.OK);
	}
}
