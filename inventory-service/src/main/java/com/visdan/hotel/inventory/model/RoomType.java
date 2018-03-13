package com.visdan.hotel.inventory.model;

public enum RoomType {
	SINGLE(150.00),
	DOUBLE(200.00),
	QUEEN(250.00),
	KING(300.00),
	JUNIOR_SUITE(500.00),
	HONEYMOON_SUITE(750.00);
	
	private double basePrice;
	
	public double getBasePrice() {
		return basePrice;
	}

	private RoomType(double basePrice) {
		this.basePrice = basePrice;
	}
}
