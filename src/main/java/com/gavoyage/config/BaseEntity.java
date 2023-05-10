package com.gavoyage.config;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {
	public Character status;
	public LocalDate createdAt;
	public LocalDate modifiedAt;
}
