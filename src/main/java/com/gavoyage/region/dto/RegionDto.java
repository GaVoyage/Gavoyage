package com.gavoyage.region.dto;

public class RegionDto {
	private int sido_code;
	private String sido_name;
	private int gugun_code;
	private String gugun_name;
	private String address;
	private int content_type_id;
	
	public RegionDto() {
		super();
	}

	public RegionDto(int sido_code, String sido_name, int gugun_code, String gugun_name, String address,
			int content_type_id) {
		super();
		this.sido_code = sido_code;
		this.sido_name = sido_name;
		this.gugun_code = gugun_code;
		this.gugun_name = gugun_name;
		this.address = address;
		this.content_type_id = content_type_id;
	}

	
	public RegionDto(int sido_code, int gugun_code, int content_type_id) {
		super();
		this.sido_code = sido_code;
		this.gugun_code = gugun_code;
		this.content_type_id = content_type_id;
	}

	
	
	@Override
	public String toString() {
		return "RegionDto [sido_code=" + sido_code + ", sido_name=" + sido_name + ", gugun_code=" + gugun_code
				+ ", gugun_name=" + gugun_name + ", address=" + address + ", content_type_id=" + content_type_id + "]";
	}

	public int getSido_code() {
		return sido_code;
	}

	public void setSido_code(int sido_code) {
		this.sido_code = sido_code;
	}

	public String getSido_name() {
		return sido_name;
	}

	public void setSido_name(String sido_name) {
		this.sido_name = sido_name;
	}

	public int getGugun_code() {
		return gugun_code;
	}

	public void setGugun_code(int gugun_code) {
		this.gugun_code = gugun_code;
	}

	public String getGugun_name() {
		return gugun_name;
	}

	public void setGugun_name(String gugun_name) {
		this.gugun_name = gugun_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getContent_type_id() {
		return content_type_id;
	}

	public void setContent_type_id(int content_type_id) {
		this.content_type_id = content_type_id;
	}
	
	
	
	
}
