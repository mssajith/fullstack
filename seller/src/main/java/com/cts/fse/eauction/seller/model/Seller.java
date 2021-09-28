package com.cts.fse.eauction.seller.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class Seller {
	// Ø First Name Ø Last Name Ø Address Ø City Ø State Ø Pin Ø Phone Ø Email

	@Size(min = 5, max = 30)
	@NotBlank(message = "First Name is Mandatory")
	private String firstName;
	@Size(min = 3, max = 23)
	@NotBlank(message = "Last Name is Mandatory")
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String pin;
	@Size(min = 10, max = 10)
	private String phoneNumber;
	@NotBlank(message = "Email is Mandatory")
	private String email;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
