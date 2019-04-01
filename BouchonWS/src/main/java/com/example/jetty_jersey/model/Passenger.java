package com.example.jetty_jersey.model;

public class Passenger extends User {

	public Passenger(String firstName, String lastName, String mail, String password) {
		super(firstName, lastName, mail, password);
	}

	public Passenger() {
		super();
	}

}