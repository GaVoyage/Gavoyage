package com.gavoyage.follow.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFollowReq {
	private Long userIdx;
	private Long targetUserIdx;
}
