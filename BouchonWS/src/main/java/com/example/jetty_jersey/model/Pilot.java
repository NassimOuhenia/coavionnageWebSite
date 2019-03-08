package com.example.jetty_jersey.model;


public class Pilot {

	private String mail;
	private double experience;
	private String certificate;

	public Pilot(String m,double exp,String cert) {
		mail = m;
		experience = exp;
		certificate=cert;
	}
	
	public Pilot() {}

	public String getMail() {
		return mail;
	}

	public void setMail(String m) {
		mail = m;
	}

	public double getExperiences() {
		return experience;
	}

	public void setExperiences(double exp) {
		experience = exp;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

}