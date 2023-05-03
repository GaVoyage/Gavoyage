package com.gavoyage.region.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class AttractionInfo {

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

	public AttractionInfo(int content_id, int content_type_id, String title, String addr1, String addr2, String zipcode,
			String tel, String first_image, String first_image2, int readcount, double latitude, double longitude,
			String mLevel, int sido_code, int gugun_code) {
		super();
		this.content_id = content_id;
		this.content_type_id = content_type_id;
		this.title = title;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.zipcode = zipcode;
		this.tel = tel;
		this.first_image = first_image;
		this.first_image2 = first_image2;
		this.readcount = readcount;
		this.latitude = latitude;
		this.longitude = longitude;
		this.mlevel = mLevel;
		this.sido_code = sido_code;
		this.gugun_code = gugun_code;
	}

	public AttractionInfo( int sido_code, int gugun_code, int content_type_id) {
		super();
		this.content_type_id = content_type_id;
		this.sido_code = sido_code;
		this.gugun_code = gugun_code;
	}
}
