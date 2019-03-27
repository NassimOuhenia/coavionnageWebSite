package com.example.jetty_jersey.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Flight {

	private String idFlight;
	private Date date;
	private String departureAirport;
	private String arrivalAirport;
	private double travelTime;
	private double price;
	private String typeflight;
	private Plane plane;
	private Pilot pilot;
	private List<Passenger> passagers;

	public Flight(String idFlight, Date date, String departureAirport, String arrivalAirport, double travelTime,
			double price, String typeflight, Plane plane, Pilot pilot) {
		super();
		this.idFlight = idFlight;
		this.date = date;
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
		this.travelTime = travelTime;
		this.price = price;
		this.typeflight = typeflight;
		this.plane = plane;
		this.pilot = pilot;
		passagers = new ArrayList<Passenger>();
	}

	public Flight() {
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDeparture_airport() {
		return departureAirport;
	}

	public void setDeparture_airport(String departure_airport) {
		this.departureAirport = departure_airport;
	}

	public String getArrival_airport() {
		return arrivalAirport;
	}

	public void setArrival_airport(String arrival_airport) {
		this.arrivalAirport = arrival_airport;
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
