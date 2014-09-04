/*******************************************************************************
 * Copyright © 2014 Progress Software Corporation.  All Rights Reserved.
 ******************************************************************************/
package com.progress.cloudservices.SystemSelfTest;

import java.io.Serializable;

public class PCNAddress implements Serializable {
	private static final long serialVersionUID = 3326477425153994009L;

	private String FirstName;
	private String LastName;
	private String Address1;
	private String Address2;
	private String id;
	private String City;
	private String CountrySub;
	private String PostCode;
	private String CountryCode;
	private long AccountNum;
	private String Email;

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getAddress1() {
		return Address1;
	}

	public void setAddress1(String address1) {
		Address1 = address1;
	}

	public String getAddress2() {
		return Address2;
	}

	public void setAddress2(String address2) {
		Address2 = address2;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getCountrySub() {
		return CountrySub;
	}

	public void setCountrySub(String countrySub) {
		CountrySub = countrySub;
	}

	public String getPostCode() {
		return PostCode;
	}

	public void setPostCode(String postCode) {
		PostCode = postCode;
	}

	public String getCountryCode() {
		return CountryCode;
	}

	public void setCountryCode(String countryCode) {
		CountryCode = countryCode;
	}

	public long getAccountNum() {
		return AccountNum;
	}

	public void setAccountNum(long accountNum) {
		AccountNum = accountNum;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

}
