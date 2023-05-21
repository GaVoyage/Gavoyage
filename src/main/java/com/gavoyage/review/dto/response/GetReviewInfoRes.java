package com.gavoyage.review.dto.response;

import java.time.LocalDate;
import java.util.List;

import com.gavoyage.region.domain.AttractionInfo;

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
	private LocalDate createdAt;
	private List<AttractionInfo> recommendsAttractionInfo;
	private List<AttractionInfo> unrecommendsAttractionInfo;
}
