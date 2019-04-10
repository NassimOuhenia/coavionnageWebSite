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
import com.example.jetty_jersey.DAO.ReservationDAO;
import com.example.jetty_jersey.model.Reservation;

@Path("/book")
public class ReservationRessource {
    
    private ReservationDAO daoReservation = DAOFactory.getInstance().getReservationDAO();
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/passenger")
    public List<Reservation> getReservationPassenger (@HeaderParam("token") String token, String idPassenger) {
	if (JwTokenHelper.getInstance().isTokenInvalid(token) ||
		!JwTokenHelper.getInstance().getUserType(token).equals("passenger")) {
	    return null;
	}
	return daoReservation.getListPassenger(idPassenger);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/passenger/confirmed")
    public List<Reservation> getReservationPassengerConfirmed(@HeaderParam("token") String token, String idPassenger) {
	if (JwTokenHelper.getInstance().isTokenInvalid(token) ||
		!JwTokenHelper.getInstance().getUserType(token).equals("passenger")) {
	    return null;
	}
	return daoReservation.getListPassenger(idPassenger);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/pilot")
    public List<Reservation> getReservationPilot(@HeaderParam("token") String token, String idPassenger) {
	if (JwTokenHelper.getInstance().isTokenInvalid(token) ||
		!JwTokenHelper.getInstance().getUserType(token).equals("pilots")) {
	    return null;
	}
	return daoReservation.getListPilot(idPassenger);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/pilot/confirmed")
    public List<Reservation> getReservationPilotConfirmed(@HeaderParam("token") String token, String idPassenger) {
	if (JwTokenHelper.getInstance().isTokenInvalid(token) ||
		!JwTokenHelper.getInstance().getUserType(token).equals("pilot")) {
	    return null;
	}
	return daoReservation.getListPilotConfirmed(idPassenger);
    }
}
