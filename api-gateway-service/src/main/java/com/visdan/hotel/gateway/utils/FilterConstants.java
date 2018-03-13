package com.visdan.hotel.gateway.utils;

public enum FilterConstants {
	TRACE_ID("hotel-trace-id"),
	AUTHORIZATION_TOKEN("Authorization"),
	PRE_FILTER_TYPE("pre"),
	POST_FILTER_TYPE("post");
	
	private String value;
	
	public String getValue() {
		return value;
	}
	
	private FilterConstants(String value) {
		this.value = value;
	}
}
