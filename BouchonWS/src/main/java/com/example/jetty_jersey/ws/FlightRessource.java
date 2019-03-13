package com.example.jetty_jersey.ws;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.example.jetty_jersey.DAO.DAOFactory;
import com.example.jetty_jersey.DAO.FlightDAO;
import com.example.jetty_jersey.model.Flight;
import com.example.jetty_jersey.model.Pilot;
import com.example.jetty_jersey.model.Plane;


@Path("/flights")
public class FlightRessource {

	private FlightDAO daoFlight = DAOFactory.getInstance().getFlightDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/flight/")
	public Flight getExample(@PathParam("id") List<String> id) {
		
		System.out.println(id);
		//just test
		return daoFlight.get().get(0);
		
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/flight")
	public List<Flight> retrieveExample(@FormParam("departure") String departure,@FormParam("arrival") String arrival
			,@FormParam("date") String date,@FormParam("travel") String travel,@FormParam("local") String local) {
		
		return daoFlight.search(date, departure, arrival, local, travel);
	}

	
	
}