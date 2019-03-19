package com.example.jetty_jersey.model;

public class Pilot extends Passenger{

	private int experience;
	private String certificate;

	public Pilot() {
		super();
	}

	public Pilot(String firstName, String lastName, String mail, String password, int experience, String certificate) {

		super(firstName,lastName,mail,password);
		this.experience = experience;
		this.certificate = certificate;
	}


	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

}