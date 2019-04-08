package com.example.jetty_jersey.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
    public List<Reservation> getReservationPassenger (String idPassenger) {
	return daoReservation.getListPassenger(idPassenger);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/passenger/confirmed")
    public List<Reservation> getReservationPassengerConfirmed(String idPassenger) {
	return daoReservation.getListPassenger(idPassenger);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/pilot")
    public List<Reservation> getReservationPilot(String idPassenger) {
	return daoReservation.getListPilot(idPassenger);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/pilot/confirmed")
    public List<Reservation> getReservationPilotConfirmed(String idPassenger) {
	return daoReservation.getListPilotConfirmed(idPassenger);
    }
}
