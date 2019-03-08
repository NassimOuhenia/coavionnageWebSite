package com.example.jetty_jersey.model;

public class Passenger {
	private String mail;

	public Passenger(String m) {
		mail = m;

	}
	
	public Passenger() {}

	public String getMail() {
		return mail;
	}

	public void setMail(String m) {
		mail = m;
	}

}