package com.visdan.hotel.inventory.model;

import java.time.Month;

public enum Season {
	SUMMER(0.25), FALL(0.05), WINTER(-0.1), SPRING(0.0);

	private double rate;

	public double getRate() {
		return rate;
	}

	private Season(double rate) {
		this.rate = rate;
	}

	public static Season of(Month month) {
		switch (month) {

		case JANUARY:
			return Season.WINTER;
		case FEBRUARY:
			return Season.WINTER;
		case MARCH:
			return Season.SPRING;
		case APRIL:
			return Season.SPRING;
		case MAY:
			return Season.SPRING;
		case JUNE:
			return Season.SUMMER;
		case JULY:
			return Season.SUMMER;
		case AUGUST:
			return Season.SUMMER;
		case SEPTEMBER:
			return Season.FALL;
		case OCTOBER:
			return Season.FALL;
		case NOVEMBER:
			return Season.FALL;
		case DECEMBER:
			return Season.WINTER;
		default:
			return Season.SPRING;
		}
	}
}
