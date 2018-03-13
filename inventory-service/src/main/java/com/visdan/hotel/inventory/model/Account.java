package com.visdan.hotel.inventory.model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

public class Account implements Serializable {
	private static final long serialVersionUID = 4681710475296674500L;
	
	private Long id;
	private String username;
	private String accountNumber;
	private Set<Address> addresses = new LinkedHashSet<>();
	private Set<CreditCard> creditCards = new LinkedHashSet<>();
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
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
	
	public Set<Address> getAddresses() {
		return addresses;
	}
	
	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}
	
	public Set<CreditCard> getCreditCards() {
		return creditCards;
	}
	
	public void setCreditCards(Set<CreditCard> creditCards) {
		this.creditCards = creditCards;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", username=" + username + ", accountNumber=" + accountNumber + ", addresses="
				+ addresses + ", creditCards=" + creditCards + "]";
	}
}
