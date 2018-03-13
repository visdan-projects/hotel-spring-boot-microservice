package com.visdan.hotel.inventory.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class RoomBooking implements Serializable {
	private static final long serialVersionUID = -7621201974460727230L;
	
	@Id
	private String id;
	private String username;
	private String accountNumber;
	private String start;
	private String end;
	
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
	
	public String getStart() {
		return start;
	}
	
	public void setStart(String start) {
		this.start = start;
	}
	
	public String getEnd() {
		return end;
	}
	
	public void setEnd(String end) {
		this.end = end;
	}
	
	@Override
	public String toString() {
		return "RoomBooking [id=" + id + ", username=" + username + ", accountNumber=" + accountNumber + ", start="
				+ start + ", end=" + end + "]";
	}
}
