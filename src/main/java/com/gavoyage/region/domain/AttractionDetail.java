package com.gavoyage.region.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
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

	public AttractionDetail(int contentId, String cat1, String cat2, String cat3, String createdTime,
			String modifiedTime, String bookTour) {
		super();
		this.content_id = contentId;
		this.cat1 = cat1;
		this.cat2 = cat2;
		this.cat3 = cat3;
		this.created_time = createdTime;
		this.modified_time = modifiedTime;
		this.booktour = bookTour;
	}

}
