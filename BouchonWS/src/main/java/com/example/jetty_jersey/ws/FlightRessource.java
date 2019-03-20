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

@Path("/flights")
public class FlightRessource {

	public static class Recherche {
		String type_local;
		String type_travel;
		String date;
		String departure;
		String arrival;
	}
	
	public static class Reservation{
		String idPassenger;
		String idFlight;
		int numberPlace;
	}
	
	private FlightDAO daoFlight = DAOFactory.getInstance().getFlightDAO();

	/*@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/flight")
	public List<Flight> getExample(@QueryParam("departure") String departure, @QueryParam("arrival") String arrival,
			@QueryParam("date") String date, @QueryParam("travel") String travel, @QueryParam("local") String local) {
		return daoFlight.search(date, departure, arrival, local, travel);

	}*/
	
	//recherche d'un vol
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/search")
	public List<Flight> searchFlight(Recherche r) {
		return daoFlight.search(r.type_local,r.type_travel,r.date,r.departure,r.arrival);
	}
	
	//planifié un vol
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
	@Path("/add")
	public void postFlight(Flight f) {
		System.out.println(f.getDate()+" "+f.getDeparture_airport());
		daoFlight.put(f);
	}
	
	//reserver un vol
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
	@Path("/book")
	public void bookFlight(Reservation r) {
		//daoFlight.put();
	}

}