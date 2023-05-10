package com.gavoyage.region.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Gugun {

	private int gugunCode;
	private String gugunName;
	private int sidoCode;

	public Gugun(int sidoCode) {
		super();
		this.sidoCode = sidoCode;
	}
}
