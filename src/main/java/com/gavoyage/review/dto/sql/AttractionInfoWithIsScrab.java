package com.gavoyage.review.dto.sql;

import com.gavoyage.region.domain.AttractionInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttractionInfoWithIsScrab extends AttractionInfo{
	private int isScrab;
}
