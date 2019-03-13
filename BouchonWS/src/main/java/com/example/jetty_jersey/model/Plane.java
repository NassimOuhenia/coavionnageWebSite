package com.example.jetty_jersey.model;

public class Plane {

	private int idplane;
	private String modele;
	private String name;
	private int numberplace;

	public Plane(int nbP, String md, String nm, int nbplace) {
		idplane = nbP;
		modele = md;
		name = nm;
		numberplace = nbplace;
	}

	public Plane() {
	}

	public int getIdplane() {
		return idplane;
	}

	public void setIdplane(int idplane) {
		this.idplane = idplane;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String m) {
		modele = m;
	}

	public String getName() {
		return name;
	}

	public void setName(String m) {
		name = m;
	}

	public int getNumberplace() {
		return numberplace;
	}

	public void setNumberplace(int numberplace) {
		this.numberplace = numberplace;
	}

}