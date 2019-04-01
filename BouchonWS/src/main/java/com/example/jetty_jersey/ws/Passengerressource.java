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
	public Passenger createPilot(User passenger) {
		Passenger ps=new Passenger(passenger.getFirstName(), passenger.getLastName(), passenger.getMail(), passenger.getPassword());
		
		if(daoPassenger.put(ps)) {
			//System.out.println("hhhhh");
		return ps;	
		}else {
			return null;
		}
		
	
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/signin")
	public Passenger loginpilot(User p) {
		
		Passenger pas=new Passenger(p.getFirstName(), p.getLastName(), p.getMail(), p.getPassword());
		
		return daoPassenger.SearchPassenger(pas);
		
	}
}
