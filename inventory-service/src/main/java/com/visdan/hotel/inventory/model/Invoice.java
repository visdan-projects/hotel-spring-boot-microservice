package com.visdan.hotel.inventory.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Invoice implements Serializable {
	private static final long serialVersionUID = -2860112419283921128L;

	@Id
	private String id;
	private String username;
	private List<Reservation> reservations;
	private Address address;
	private InvoiceStatus status;
	
	public Invoice(String username, Address address) {
		this.username = username;
		this.address = address;
		this.address.setAddressType(AddressType.BILLING);
		this.status = InvoiceStatus.CREATED;
		this.reservations = new ArrayList<>();
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
	
	public List<Reservation> getReservations() {
		return reservations;
	}
	
	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public InvoiceStatus getStatus() {
		return status;
	}
	
	public void setStatus(InvoiceStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", username=" + username + ", reservations=" + reservations + ", address="
				+ address + ", status=" + status + "]";
	}
}
