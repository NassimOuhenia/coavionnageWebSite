package com.example.jetty_jersey.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.jetty_jersey.DAO.DAOFactory;
import com.example.jetty_jersey.DAO.PassengerDAO;
import com.example.jetty_jersey.DAO.PilotDAO;
import com.example.jetty_jersey.model.Passenger;
import com.example.jetty_jersey.model.Pilot;
import com.example.jetty_jersey.model.User;


@Path("/user/passenger/")
public class Passengerressource {
	private PassengerDAO daoPassenger = DAOFactory.getInstance().getPassengerDAO();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/signup")
	public Passenger createPassenger(Passenger passenger) {
		if(daoPassenger.put(passenger))
			System.out.println("pasenger ajouter");
		else
			System.out.println("pasenger pas ajouter");
		return passenger;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/signin")
	public Passenger loginPassenger(Passenger passenger) {
		Passenger p=daoPassenger.searchPassenger(passenger);
		if(p==null)
			System.out.println("je ne t'ai pas trouvé");
		else
			System.out.println("autorisation d'entré");
		return p;
	}
}