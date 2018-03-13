package com.visdan.hotel.inventory.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Reservation implements Serializable {
	private static final long serialVersionUID = 4212830598107462191L;

	@Id
	private String id;
	private String username;
	private String accountNumber;
	private ReservationStatus status;
	private List<Room> rooms;
	
	public Reservation() {
		this.status = ReservationStatus.PENDING;
		this.rooms = new ArrayList<>();
	}
	
	public Reservation(String accountNumber, String username) {
		this();
		this.accountNumber = accountNumber;
		this.username = username;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public ReservationStatus getStatus() {
		return status;
	}
	
	public void setStatus(ReservationStatus status) {
		this.status = status;
	}
	
	public List<Room> getRooms() {
		return rooms;
	}
	
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", username=" + username + ", accountNumber=" + accountNumber + ", status="
				+ status + ", rooms=" + rooms + "]";
	}
}
