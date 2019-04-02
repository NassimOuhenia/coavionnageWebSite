package com.example.jetty_jersey.model;

public class Plane {

	private int idPlane;
	private String modele;
	private String name;
	private int numberPlace;

	public Plane(int idPlane, String modele, String name, int numberPlace) {
		this.idPlane = idPlane;
		this.modele = modele;
		this.name = name;
		this.numberPlace = numberPlace;
	}

	public Plane() {
	}

	public int getIdPlane() {
		return idPlane;
	}

	public void setIdPlane(int idPlane) {
		this.idPlane = idPlane;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberPlace() {
		return numberPlace;
	}

	public void setNumberPlace(int numberPlace) {
		this.numberPlace = numberPlace;
	}

}