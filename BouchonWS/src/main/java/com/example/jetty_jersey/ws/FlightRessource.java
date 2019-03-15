package com.example.jetty_jersey.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
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

	public static class SampleRequest{
		public String firstName;
		public String lastName;
	}
	
	private FlightDAO daoFlight = DAOFactory.getInstance().getFlightDAO();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/flight")
	public List<Flight> getExample(@PathParam("departure") String departure, @PathParam("arrival") String arrival,
			@PathParam("date") String date, @PathParam("travel") String travel, @PathParam("local") String local) {

		// just test
		return daoFlight.search(date, departure, arrival, local, travel);

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/flight")
	public List<Flight> retrieveExample(SampleRequest request) {
		return null;
	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/flight-xml")
	public List<Flight> retrieveExampleXml(SampleRequest request) {
		return null;
	}

}