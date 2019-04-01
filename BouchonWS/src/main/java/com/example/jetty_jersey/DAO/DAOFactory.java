package com.example.jetty_jersey.DAO;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.example.jetty_jersey.model.Flight;
import com.example.jetty_jersey.model.Passenger;
import com.example.jetty_jersey.model.Pilot;
import com.example.jetty_jersey.model.Plane;

//classe d'instanciation de toute les dao et connection a la bdd
public class DAOFactory {

	private String url;
	private String username;
	private String password;

	private static DAOFactory instance = null;
	private static FlightDAO flightDao = null;
	private static PilotDAO pilotDao = null;
	private static PassengerDAO passengerDao = null;

	// private static TransportClient connexion;

	public DAOFactory() {
		// TODO Auto-generated constructor stub
	}

	DAOFactory(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	/* Methode chargee de fournir une connexion a la base de donnees */
	public static DAOFactory getInstance() {
		if (instance == null)
			instance = new DAOFactory("ff", "ff", "ff");
		return instance;
	}

	/* Methodes de recuperation de l'implementation des differents DAO */

	public PilotDAO getPiloteDAO() {
		if (pilotDao == null)
			pilotDao = new PilotDAO(this);
		return pilotDao;
	}

	public PlaneDAO getPlaneDAO() {
		return new PlaneDAO(this);
	}

	public PassengerDAO getPassengerDAO() {
		if (passengerDao == null)
			passengerDao = new PassengerDAO(this);
		return passengerDao;
	}

	public FlightDAO getFlightDAO() {
		if (flightDao == null)
			flightDao = new FlightDAO(this);
		return flightDao;
	}

}