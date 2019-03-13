package com.example.jetty_jersey.DAO;

import com.example.jetty_jersey.model.Flight;
import com.example.jetty_jersey.model.Passenger;
import com.example.jetty_jersey.model.Pilot;
import com.example.jetty_jersey.model.Plane;
//classe d'instanciation de toute les dao et connection a la bdd
public class DAOFactory {


	private String url;

	private String username;

	private String password;
	
	public DAOFactory() {
		// TODO Auto-generated constructor stub
	}

	DAOFactory(String url, String username, String password) {

		this.url = url;

		this.username = username;

		this.password = password;

	}

	/*
	 * 
	 * M�thode charg�e de r�cup�rer les informations de connexion � la base de
	 * 
	 * donn�es, charger le driver JDBC et retourner une instance de la Factory
	 * 
	 */

	public static DAOFactory getInstance() {

		

		DAOFactory instance = new DAOFactory("ff", "ff", "ff");

		return instance;

	}

	/* M�thode charg�e de fournir une connexion � la base de donn�es */


	/*
	 * 
	 * M�thodes de r�cup�ration de l'impl�mentation des diff�rents DAO
	 * 
	 */

	public PilotDAO getPiloteDAO() {
		return new PilotDAO(this);
	}
	
	public PlaneDAO getPlaneDAO() {
		return new PlaneDAO(this);
	}
	
	public PassengerDAO getPassengerDAO() {
		return new PassengerDAO(this);
	}
	
	public FlightDAO getFlightDAO() {
		return new FlightDAO(this);
	}

}