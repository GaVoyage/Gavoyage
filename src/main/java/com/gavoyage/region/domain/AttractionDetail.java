package com.gavoyage.region.domain;

import com.gavoyage.config.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttractionDetail {

	private int content_id;
	private String cat1;
	private String cat2;
	private String cat3;
	private String created_time;
	private String modified_time;
	private String booktour;
	
	public AttractionDetail(int contentId) {
		super();
		this.content_id = contentId;
	}

}
