package com.example.jetty_jersey.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.jetty_jersey.DAO.DAO;
import com.example.jetty_jersey.DAO.DAOFactory;
import com.example.jetty_jersey.DAO.PassengerDAO;
import com.example.jetty_jersey.DAO.PilotDAO;
import com.example.jetty_jersey.model.Passenger;
import com.example.jetty_jersey.model.Pilot;

@Path("/passenger")
public class UserRessource {

	private PassengerDAO daoPassenger = DAOFactory.getInstance().getPassengerDAO();
	private PilotDAO daoPilot = DAOFactory.getInstance().getPiloteDAO();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	
	@Path("/passenger")
	public List<Passenger> getExample() {
		return daoPassenger.get();
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/signup")
	public String newUser(@FormParam("type") String type, @FormParam("fName") String firstName,
			@FormParam("lName") String lastName, @FormParam("email") String email,
			@FormParam("password") String password, @FormParam("cPassword") String confirmPassword) {
		
		if(!password.trim().equals(confirmPassword.trim())) {
			return null;
		}
		if(type.equals("passenger")) {
			Passenger p = new Passenger(firstName,lastName,email,password);
			daoPassenger.put(p);
		} else {
			Pilot p = new Pilot(firstName, lastName, email, password, 0, null);
			daoPilot.put(p);
			
		}
		return null;
	}

}