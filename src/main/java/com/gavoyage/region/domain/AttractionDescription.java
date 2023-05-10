package com.gavoyage.region.domain;

import com.gavoyage.config.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttractionDescription {

	private int content_id;
	private String homepage;
	private String overview;
	private String telname;

	
	public AttractionDescription(int contentId) {
		super();
		this.content_id = contentId;
	}

}
