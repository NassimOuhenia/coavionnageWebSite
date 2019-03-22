package com.example.jetty_jersey.model;

import java.util.ArrayList;
import java.util.List;

public class Flight {

	private String idFlight;
	private String date;
	private String departureAirport;
	private String arrivalAirport;
	private double travelTime;
	private double price;
	private String timep;
	private String typeflight;
	private Plane plane;
	private Pilot pilot;
	private int seatLeft;
	private List<Passenger> passagers;
	
	
	
	public Flight(String idFlight, String date, String departureAirport, String arrivalAirport, double travelTime,
			double price, String time, String typeflight, Plane plane, Pilot pilot, int seatLeft) {
		this.idFlight = idFlight;
		this.date = date;
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
		this.travelTime = travelTime;
		this.price = price;
		this.timep = time;
		this.typeflight = typeflight;
		this.plane = plane;
		this.pilot = pilot;
		this.seatLeft = seatLeft;
		passagers = new ArrayList<Passenger>(); 
	}

	public Flight() {
	}

	
	public String getTimep() {
		return timep;
	}

	public void setTimep(String time) {
		this.timep = time;
	}

	public int getSeatLeft() {
		return seatLeft;
	}

	public void setSeatLeft(int seatLeft) {
		this.seatLeft = seatLeft;
	}

	public String getIdFlight() {
		return idFlight;
	}

	public void setIdFlight(String idFlight) {
		this.idFlight = idFlight;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}

	public String getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
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

	

}