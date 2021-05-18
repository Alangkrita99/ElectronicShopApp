package com.cg.eshop.dto;

public class CustomerDto {
	private Integer customerId;
	private String customerName;
	private String customerEmail;
	private String customerPhone;
	private String addressLine;
	private String postalCode;
	private String city;
	private String state;
	private String country;
	
	
	
	public CustomerDto() {
		super();
	}



	public CustomerDto(Integer customerId, String customerName, String customerEmail, String customerPhone,
			String addressLine, String postalCode, String city, String state, String country) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerPhone = customerPhone;
		this.addressLine = addressLine;
		this.postalCode = postalCode;
		this.city = city;
		this.state = state;
		this.country = country;
	}



	public CustomerDto(String customerName, String customerEmail, String customerPhone, String addressLine,
			String postalCode, String city, String state, String country) {
		super();
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerPhone = customerPhone;
		this.addressLine = addressLine;
		this.postalCode = postalCode;
		this.city = city;
		this.state = state;
		this.country = country;
	}



	public Integer getCustomerId() {
		return customerId;
	}



	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}



	public String getCustomerName() {
		return customerName;
	}



	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}



	public String getCustomerEmail() {
		return customerEmail;
	}



	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}



	public String getCustomerPhone() {
		return customerPhone;
	}



	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}



	public String getAddressLine() {
		return addressLine;
	}



	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}



	public String getPostalCode() {
		return postalCode;
	}



	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}
	
	
	
	

}
