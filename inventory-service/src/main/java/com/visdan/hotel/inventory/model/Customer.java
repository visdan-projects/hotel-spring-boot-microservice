package com.visdan.hotel.inventory.model;

import java.io.Serializable;

public class Customer implements Serializable {
	private static final long serialVersionUID = 48203767843068499L;
	
	private Long id;
	private Account account;
	private String email;
	private String firstName;
	private String lastName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", account=" + account + ", email=" + email + ", firstName=" + firstName
				+ ", lastName=" + lastName + "]";
	}
}
