package com.example.jetty_jersey.ws;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.jetty_jersey.JwTokenHelper;
import com.example.jetty_jersey.DAO.DAOFactory;
import com.example.jetty_jersey.DAO.FlightDAO;
import com.example.jetty_jersey.DAO.ReservationDAO;
import com.example.jetty_jersey.model.Flight;
import com.example.jetty_jersey.model.Recherche;
import com.example.jetty_jersey.model.Reservation;
//import com.example.jetty_jersey.model.Pilot;

import com.example.jetty_jersey.model.ID;

@Path("/flights")
public class FlightRessource {

	private FlightDAO daoFlight = DAOFactory.getInstance().getFlightDAO();
	private ReservationDAO daoReservation = DAOFactory.getInstance().getReservationDAO();

	// planifié un vol
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/add")
	public String postFlight(@HeaderParam("token") String token, Flight f) {
		if (JwTokenHelper.getInstance().isTokenInvalid(token)) {
		    return "{" + "\"status\":\"403\"," + "\"error\":\"Your token is not valid. Try to reconnect.\"" + "}";
		}
		else if (!JwTokenHelper.getInstance().getUserType(token).equals("pilot")) {
		    return "{" + "\"status\":\"403\"," + "\"error\":\"You dont have the permission\"" + "}";
		}
		String id = JwTokenHelper.getInstance().getIdFromToken(token);
		f.setPilot(DAOFactory.getInstance().getPiloteDAO().get(id).get(0));
		return daoFlight.put(f);
	}

	// Recuperer infos d'un vol
	// reserver un vol
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
	public String book(@HeaderParam("token") String token, Reservation r) {
	    	if (JwTokenHelper.getInstance().isTokenInvalid(token)) {
		    return "{" + "\"status\":\"403\"," + "\"error\":\"Your token is not valid. Try to reconnect.\"" + "}";
		}
		else if (!JwTokenHelper.getInstance().getUserType(token).equals("passenger")) {
		    return "{" + "\"status\":\"403\"," + "\"error\":\"You dont have the permission\"" + "}";
		}
	    	
		String response = daoReservation.put(r);
		if (response.contains("400")) {
			return response;
		}
		// Pilot pilot = daoFlight.get(r.getIdFlight()).get(0).getPilot();
		// String emailPilot = pilot.getMail();
		// Envoyer email
		return response;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/search")
	public List<Flight> search(Recherche r) {
		List<Flight> list =  daoFlight.get(r);
		for(Flight f : list) {
			System.out.println("ws merde "+f.getIdFlight());
		}
		return list;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/book/confirm")
	public boolean confirm(ID idReservation) {
		return daoReservation.update(null, idReservation.getId());
	}

}
