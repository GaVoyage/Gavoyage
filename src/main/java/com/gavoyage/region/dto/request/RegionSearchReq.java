package com.gavoyage.region.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegionSearchReq {
	private int sido_code;
	private int gugun_code;
	private int content_type_id;
}
