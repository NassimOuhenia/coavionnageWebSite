package com.example.jetty_jersey.model;

public class User {
	private String firstName;
	private String lastName;
	private String mail;
	private String password;
	
	public User() {}
	
	public User( String FirstNam,  String LastNam , String ma,String pass) {
		firstName=FirstNam;
		lastName=LastNam;
		mail=ma;
		password=pass;
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstNam) {
		firstName = firstNam;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastNam) {
		lastName = lastName;
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
