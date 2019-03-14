package com.example.jetty_jersey.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.QueryParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
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
	@Path("/flight")
	public List<Flight> getExample(@QueryParam("departure") String departure, @QueryParam("arrival") String arrival,
			@QueryParam("date") String date, @QueryParam("travel") String travel, @QueryParam("local") String local) {

		// just test
		return daoFlight.search(date, departure, arrival, local, travel);

	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/flight")
	public List<Flight> retrieveExample() {
		return null;
	}

}