package com.gavoyage.review.dto.response;

import java.time.LocalDate;
import java.util.List;

import com.gavoyage.region.domain.AttractionInfo;
import com.gavoyage.review.dto.sql.AttractionInfoWithIsScrab;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetReviewInfoRes {
	private Long reviewIdx;
	private String writerName;
	private String title;
	private String contents;
	private int hit;
	private int isLiked;
	private LocalDate createdAt;
	private List<AttractionInfoWithIsScrab> recommendsAttractionInfo;
	private List<AttractionInfoWithIsScrab> unrecommendsAttractionInfo;
}
