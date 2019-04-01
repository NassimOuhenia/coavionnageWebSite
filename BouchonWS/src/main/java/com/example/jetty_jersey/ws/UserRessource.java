package com.example.jetty_jersey.ws;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.jetty_jersey.DAO.DAOFactory;
import com.example.jetty_jersey.DAO.PassengerDAO;
import com.example.jetty_jersey.DAO.PilotDAO;
import com.example.jetty_jersey.model.Passenger;
import com.example.jetty_jersey.model.Pilot;

@Path("/passengers")
public class UserRessource {

	private PassengerDAO daoPassenger = DAOFactory.getInstance().getPassengerDAO();

	@POST
	@Path("/get")
	public List<Passenger> getUser(ID identification) {
	    return daoPassenger.get(identification.getId());
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/signup")
	public String signup(Passenger p) {
	    return daoPassenger.put(p);
	}

}