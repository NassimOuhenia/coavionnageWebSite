package com.example.jetty_jersey.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Flight {
	
	private String idFlight;
	private String date;
	private String departure_airport;
	private String arrival_airport;
	private double travelTime;
	private double price;
	private String typeflight;
	private Plane plane;
	private Pilot pilot;
	private List<Passenger> passagers;
	
	public Flight(String idFlight, String date, String departure_airport, String arrival_airport, double travelTime,
			double price, String typeflight, Plane plane, Pilot pilot) {
		super();
		this.idFlight = idFlight;
		this.date = date;
		this.departure_airport = departure_airport;
		this.arrival_airport = arrival_airport;
		this.travelTime = travelTime;
		this.price = price;
		this.typeflight = typeflight;
		this.plane = plane;
		this.pilot = pilot;
		passagers = new ArrayList<Passenger>();
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDeparture_airport() {
		return departure_airport;
	}

	public void setDeparture_airport(String departure_airport) {
		this.departure_airport = departure_airport;
	}

	public String getArrival_airport() {
		return arrival_airport;
	}

	public void setArrival_airport(String arrival_airport) {
		this.arrival_airport = arrival_airport;
	}

	public double getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(double travelTime) {
		this.travelTime = travelTime;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTypeflight() {
		return typeflight;
	}

	public void setTypeflight(String typeflight) {
		this.typeflight = typeflight;
	}

	public Plane getPlane() {
		return plane;
	}

	public void setPlane(Plane plane) {
		this.plane = plane;
	}

	public Pilot getPilot() {
		return pilot;
	}

	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
	}

	public List<Passenger> getPassagers() {
		return passagers;
	}

	public void setPassagers(List<Passenger> passagers) {
		this.passagers = passagers;
	}

	public String getIdFlight() {
		return idFlight;
	}

	

}