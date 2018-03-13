package com.visdan.hotel.inventory.model;

import java.io.Serializable;

public class Address implements Serializable {
	private static final long serialVersionUID = -2396872764678716919L;
	
	private Long id;
	private AddressType addressType;
	private String city;
	private String country;
	private String province;
	private String street1;
	private String street2;
	private String postalCode;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public AddressType getAddressType() {
		return addressType;
	}
	
	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getProvince() {
		return province;
	}
	
	public void setProvince(String province) {
		this.province = province;
	}
	
	public String getStreet1() {
		return street1;
	}
	
	public void setStreet1(String street1) {
		this.street1 = street1;
	}
	
	public String getStreet2() {
		return street2;
	}
	
	public void setStreet2(String street2) {
		this.street2 = street2;
	}
	
	public String getPostalCode() {
		return postalCode;
	}
	
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", addressType=" + addressType + ", city=" + city
				+ ", country=" + country + ", province=" + province + ", street1=" + street1 + ", street2=" + street2
				+ ", postalCode=" + postalCode + "]";
	}
}
