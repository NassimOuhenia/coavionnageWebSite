package com.example.jetty_jersey.model;

public class Passenger {
	private String FirstName;
	private String LastName;
	private String mail;
	private String password;

	public Passenger() {
	}

	public Passenger(String firstName, String lastName, String mail, String password) {

		FirstName = firstName;
		LastName = lastName;
		this.mail = mail;
		this.password = password;
	}

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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}