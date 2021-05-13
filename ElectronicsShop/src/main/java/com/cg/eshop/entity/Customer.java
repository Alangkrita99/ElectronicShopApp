package com.cg.eshop.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cg_customer")
public class Customer {

	@Id
	@Column(name = "cust_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String customerId;
	@Column(name = "cust_name")
	private String customerName;
	@Column(name = "cust_email")
	private String customerEmail;
	@Column(name = "cust_phone")
	private String customerPhone;
	@Column(name="address")
	private String addressLine;
	@Column(name="postal_code")
	private String postalCode;
	@Column(name="city")
	private String city;
	@Column(name="state")
	private String state;
	@Column(name="country")
	private String country;
	@OneToOne
	@JoinColumn(name="bank_acc",referencedColumnName = "card_number")
	private BankAccount bankAccount;

	@OneToMany(mappedBy = "customer")
	private List<ElectronicProductOrder> elecProds;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "basket_id",referencedColumnName = "basket_id")
	private Basket basket;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
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

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	public List<ElectronicProductOrder> getElecProds() {
		return elecProds;
	}

	public void setElecProds(List<ElectronicProductOrder> elecProds) {
		this.elecProds = elecProds;
	}

	public Basket getBasket() {
		return basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerEmail="
				+ customerEmail + ", customerPhone=" + customerPhone + ", addressLine=" + addressLine + ", postalCode="
				+ postalCode + ", city=" + city + ", state=" + state + ", country=" + country + ", bankAccount="
				+ bankAccount + ", elecProds=" + elecProds + ", basket=" + basket + "]";
	}
	

	

}