package com.gavoyage.scrap.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScrapAttractionRes {
	private Integer content_id;
	private Integer content_type_id;
	private String title;
	private String addr1;
 	private String first_image;
}
