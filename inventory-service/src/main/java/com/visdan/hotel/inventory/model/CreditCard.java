package com.visdan.hotel.inventory.model;

import java.io.Serializable;

public class CreditCard implements Serializable {
	private static final long serialVersionUID = -6819290999373895645L;
	
	private Long id;
	private Account account;
	private CreditCardType type;
	private String number;

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

	public CreditCardType getType() {
		return type;
	}

	public void setType(CreditCardType type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "CreditCard [id=" + id + ", account=" + account + ", type=" + type + ", number=" + number + "]";
	}
}
