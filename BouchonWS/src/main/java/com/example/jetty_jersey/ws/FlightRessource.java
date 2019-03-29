package com.example.jetty_jersey.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.example.jetty_jersey.DAO.DAOFactory;
import com.example.jetty_jersey.DAO.FlightDAO;
import com.example.jetty_jersey.model.Flight;
import com.example.jetty_jersey.model.Recherche;
import com.example.jetty_jersey.model.Reservation;

@Path("/flights")
public class FlightRessource {

	private FlightDAO daoFlight = DAOFactory.getInstance().getFlightDAO();

	// recherche d'un vol
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/search")
	public List<Flight> searchFlight(Recherche r) {
		System.out.println("je recherche : "+r.getTypeLocal()+" "+r.getTypeTravel()+" "+r.getDate()+" "+
				r.getDeparture()+" "+r.getArrival());
		
		List<Flight> l=daoFlight.search(r.getTypeLocal(), r.getTypeTravel(), r.getDate(), r.getDeparture(), r.getArrival());
		
		for(Flight f:l) {
			System.out.println("j'ai trouvé : "+f.getTypeflight()+" "+f.getDate()+" "+f.getDepartureAirport()
				+" "+f.getArrivalAirport()+" "+f.getTravelTime()+" "+f.getPrice());
		}
		return l;
	}

	// planifié un vol
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/add")
	public String postFlight(Flight f) {
		System.out.println("je planifie un vol");
		System.out.println(f.getTimep());
		System.out.println(f.getArrivalAirport());
		System.out.println(f.getDepartureAirport());
		
		if (daoFlight.put(f))
			return "ok";
		return "nok";
	}

	// reserver un vol
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/book")
	public void bookFlight(Reservation r) {
		// daoFlight.put();
		System.out.println("je reserve un vol");
	}

}