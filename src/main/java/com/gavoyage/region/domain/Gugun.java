package com.gavoyage.region.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class Gugun {

	private int gugunCode;
	private String gugunName;
	private int sidoCode;

	public Gugun(int sidoCode) {
		super();
		this.sidoCode = sidoCode;
	}


	public Gugun(int gugunCode, String gugunName, int sidoCode) {
		super();
		this.gugunCode = gugunCode;
		this.gugunName = gugunName;
		this.sidoCode = sidoCode;
	}
}
