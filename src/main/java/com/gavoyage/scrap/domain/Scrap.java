package com.gavoyage.scrap.domain;

import com.gavoyage.global.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Scrap extends BaseEntity{
	private Long scrapIdx;
	private Long userIdx;
	private Integer content_id;
}
