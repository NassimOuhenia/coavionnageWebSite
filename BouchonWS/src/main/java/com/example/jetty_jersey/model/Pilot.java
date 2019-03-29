package com.example.jetty_jersey.model;

import javax.print.DocFlavor.STRING;

public class Pilot extends User{

	private double experience;
	private String certificate;

	

	public Pilot(String firstName, String lastName, String mail, String password, double experience, String certificate) {

		super(firstName,lastName,mail,password);
		this.experience = experience;
		this.certificate = certificate;
	}


	public double getExperience() {
		return experience;
	}

	public void setExperience(double experience) {
		this.experience = experience;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

}