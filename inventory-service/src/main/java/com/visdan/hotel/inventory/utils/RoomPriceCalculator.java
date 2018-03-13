package com.visdan.hotel.inventory.utils;

import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.visdan.hotel.inventory.model.RoomType;
import com.visdan.hotel.inventory.model.Season;

public class RoomPriceCalculator {
	public static final double TAX = 0.10;

	public static long calculatePrice(RoomType roomType) {
		ZoneId zoneId = ZoneId.systemDefault();
		ZonedDateTime zdt = ZonedDateTime.now(zoneId);
		Month month = Month.from(zdt);
		Season season = Season.of(month);
		double base = roomType.getBasePrice();
		double rate = season.getRate();
		return Math.round((base + (base * rate)) * (1.0 + TAX));
	}
}
