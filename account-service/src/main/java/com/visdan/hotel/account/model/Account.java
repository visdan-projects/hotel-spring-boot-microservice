package com.visdan.hotel.account.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "Account")
@Table(name = "accounts")
public class Account {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "account_number", nullable = false)
	private String accountNumber;

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

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Column(nullable = false)
	@JsonManagedReference
	private Set<Address> addresses = new LinkedHashSet<>();

	public void addAddress(Address address) {
		addresses.add(address);
		address.setAccount(this);
	}

	public void removeAddress(Address address) {
		addresses.remove(address);
		address.setAccount(null);
	}

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Column(nullable = false)
	@JsonManagedReference
	private Set<CreditCard> creditCards = new LinkedHashSet<>();

	public void addCreditCard(CreditCard creditCard) {
		creditCards.add(creditCard);
		creditCard.setAccount(this);
	}

	public void removeCreditCard(CreditCard creditCard) {
		creditCards.remove(creditCard);
		creditCard.setAccount(null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", username=" + username + ", accountNumber=" + accountNumber + ", addresses="
				+ addresses + ", creditCards=" + creditCards + "]";
	}
}
