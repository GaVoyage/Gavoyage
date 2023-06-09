package com.gavoyage.region.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Gugun {

	private int gugun_code;
	private String gugun_name;
	private int sido_code;

	public Gugun(int sidoCode) {
		super();
		this.sido_code = sidoCode;
	}
}
