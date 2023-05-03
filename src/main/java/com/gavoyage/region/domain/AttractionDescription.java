package com.gavoyage.region.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class AttractionDescription {

	private int content_id;
	private String homepage;
	private String overview;
	private String telname;

	
	public AttractionDescription(int contentId) {
		super();
		this.content_id = contentId;
	}



	public AttractionDescription(int contentId, String homepage, String overview, String telName) {
		super();
		this.content_id = contentId;
		this.homepage = homepage;
		this.overview = overview;
		this.telname = telName;
	}
}
