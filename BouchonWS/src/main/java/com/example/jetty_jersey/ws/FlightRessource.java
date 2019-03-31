package com.example.jetty_jersey.ws;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.QueryParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;

import com.example.jetty_jersey.DAO.DAOFactory;
import com.example.jetty_jersey.DAO.FlightDAO;
import com.example.jetty_jersey.model.Flight;
import com.example.jetty_jersey.model.Pilot;
import com.example.jetty_jersey.model.Plane;
import com.example.jetty_jersey.model.Recherche;
import com.example.jetty_jersey.model.Reservation;
import com.example.jetty_jersey.model.ID;

@Path("/flights")
public class FlightRessource {
	
	private FlightDAO daoFlight = DAOFactory.getInstance().getFlightDAO();
	
	//recherche d'un vol
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/searchBouchon")
	public List<Flight> searchFlight(Recherche r) {
		return daoFlight.search(r.getTypeLocal(),r.getTypeTravel(),r.getDate(),r.getDeparture(),r.getArrival());
	}
	
	//planifié un vol
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/add")
	public String postFlight(Flight f) {
		return daoFlight.put(f);
	}

	
	//Recuperer infos d'un vol
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get")
	public List<Flight> get(ID identification) {
	    return daoFlight.get(identification.getId());
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/book")
	public String book(Reservation r) {
	    String response = daoFlight.book(r);
	    if (response.contains("400")) {
		return response;
	    }
	    Pilot pilot = daoFlight.get(r.getIdFlight()).get(0).getPilot();
	    String emailPilot = pilot.getMail();
	    //Envoyer email
	    
	    return response;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/search")
	public List<Flight> search(Recherche r) {
	    return daoFlight.get(r);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/book/confirm")
	public String confirm(ID idPassenger) {
		return daoFlight.updateBook(idPassenger.getId());
	}
	

}
