package com.gavoyage.review.dto.sql;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttractionInfoWithIsScrab {
	private int content_id;
	private int content_type_id;
	private String title;
	private String addr1;
	private String addr2;
	private String zipcode;
	private String tel;
	private String first_image;
	private String first_image2;
	private int readcount;
	private double latitude;
	private double longitude;
	private String mlevel;

	// foreign key
	private int sido_code;
	private int gugun_code;
	
	private int isScrab;
}
