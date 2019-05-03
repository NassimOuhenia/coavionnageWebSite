package com.example.jetty_jersey.ws;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.jetty_jersey.DAO.DAO;
import com.example.jetty_jersey.DAO.DAOFactory;
import com.example.jetty_jersey.model.Passenger;

@Path("/user")
public class UserRessource {

	private DAO<Passenger> daoPassenger = DAOFactory.getInstance().getPassengerDAO();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/passenger")
	public List<Passenger> getExample() {
		return daoPassenger.get();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/passenger")
	public String retrieveExample() {
		System.out.println("ajouté : ");
		return "ok";
	}
	
}