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
		IndexResponse response = daoFlight.put(f);
		if (response != null) {
		    if (response .status() == RestStatus.CREATED) {
			return "{" +
				"\"status\":\"201\"," +
				"\"id\":\"" + response.getId() + "\"" +
				"}";
		    }
		}
		return "{" +
		    "\"status\":\"500\"," +
		    "\"error\":\"Flight couldnt be created \"" +
		    "}";
	}

	
	//Recuperer infos d'un vol
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get")
	public String get(ID identification) {
	    Map<String, Object> map = daoFlight.get(identification.getId());
	    if (map.size() > 0) {
		return "{" +
			    "\"status\":\"200\"," +
			    "\"date\":\"" + map.get("date") + "\"," +
			    "\"departureAirport\":\"" + map.get("departureAirport") + "\"," +
			    "\"arrivalAirport\":\"" + map.get("arrivalAirport") + "\"," +
			    "\"travelTime\":\"" + map.get("travelTime") + "\"," +
			    "\"price\":\"" + map.get("price") + "\"" +
			    "\"typeflight\":\"" + map.get("typeflight") + "\"," +
			    "\"plane\":\"" + map.get("plane") + "\"," +
			    "\"pilot\":\"" + map.get("pilot") + "\"," +
			    "\"passagers\":\"" + map.get("passagers") + "\"," +
			    "}";
	    }
	    return "{" +
	    	"\"status\":\"400\"," +
	    	"\"error\":\"Can not find flight\"" +
	    	"}";
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/book")
	public String book(Reservation r) {
	    IndexResponse response = daoFlight.book(r);
	    if (!(response != null && response.status() == RestStatus.CREATED)) {
		return "{" +
		    	"\"status\":\"400\"," +
		    	"\"error\":\"Can not book the flight\"" +
		    	"}";
	    }
	    Pilot pilot = (Pilot)daoFlight.get(r.getIdFlight()).get("pilot");
	    String emailPilot = pilot.getMail();
	    //Envoyer email
	    
	    return "{" +
	    	"\"status\":\"200\"," +
	    	"\"message\":\"Well booked\"" +
	    	"}";
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/search")
	public String search(Recherche r) {
	    SearchResponse response = daoFlight.get(r);
	    SearchHit[] result = response.getHits().getHits();
	    String res = "{" +
		    	"\"status\":\"200\"," +
		    	"\"result\": {";
	    for (SearchHit sh : result) {
		Map<String, Object> map = sh.getSourceAsMap();
		res += "{" +
			"\"date\":\"" + map.get("date") + "\"," +
			"\"departureAirport\":\"" + map.get("departureAirport") + "\"," +
			"\"arrivalAirport\":\"" + map.get("arrivalAirport") + "\"," +
			"\"travelTime\":\"" + map.get("travelTime") + "\"," +
			"\"price\":\"" + map.get("price") + "\"" +
			"\"typeflight\":\"" + map.get("typeflight") + "\"," +
			"\"plane\":\"" + map.get("plane") + "\"," +
			"\"pilot\":\"" + map.get("pilot") + "\"," +
			"\"passagers\":\"" + map.get("passagers") + "\"," +
			"}";
		if (sh != result[result.length-1])
		    res += ",";
	    }
	    res += "}";
	    return res;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/book/confirm")
	public String confirm(ID idPassenger) {
		UpdateResponse response = daoFlight.updateBook(idPassenger.getId());
		if(response != null && response.status() == RestStatus.OK) {
			return "{" +
			    	"\"status\":\"200\"," +
			    	"\"message\":\"Well confirmed\"" +
			    	"}";
		}
		return "{" +
    	"\"status\":\"400\"," +
    	"\"error\":\"Can not confirm the booking\"" +
    	"}";
	}
	

}
