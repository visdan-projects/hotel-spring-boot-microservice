package com.visdan.hotel.inventory.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Room implements Serializable {
	private static final long serialVersionUID = -6426455101984467206L;

	@Id
	private String id;
	private int maxOccupancy;
	private List<RoomBooking> bookings;
	private RoomType roomType;
	private boolean smoking;
	private String roomNumber;
	private String description;
	private long price;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public int getMaxOccupancy() {
		return maxOccupancy;
	}
	
	public void setMaxOccupancy(int maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}
	
	public List<RoomBooking> getBookings() {
		return bookings;
	}
	
	public void setBookings(List<RoomBooking> bookings) {
		this.bookings = bookings;
	}
	
	public RoomType getRoomType() {
		return roomType;
	}
	
	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}
	
	public boolean isSmoking() {
		return smoking;
	}
	
	public void setSmoking(boolean smoking) {
		this.smoking = smoking;
	}
	
	public String getRoomNumber() {
		return roomNumber;
	}
	
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public long getPrice() {
		return price;
	}
	
	public void setPrice(long price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", maxOccupancy=" + maxOccupancy + ", bookings=" + bookings + ", roomType=" + roomType
				+ ", smoking=" + smoking + ", roomNumber=" + roomNumber + ", description=" + description + ", price="
				+ price + "]";
	}
}
