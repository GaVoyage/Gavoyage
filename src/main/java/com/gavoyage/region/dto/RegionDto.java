package com.gavoyage.region.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegionDto {
	private int sido_code;
	private String sido_name;
	private int gugun_code;
	private String gugun_name;
	private String address;
	private int content_type_id;
	
}
