package com.example.jetty_jersey.ws;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.jetty_jersey.DAO.DAO;
import com.example.jetty_jersey.DAO.DAOFactory;
import com.example.jetty_jersey.DAO.PilotDAO;
import com.example.jetty_jersey.model.Passenger;
import com.example.jetty_jersey.model.Pilot;
import com.example.jetty_jersey.model.User;


@Path("/user/pilots/")
public class PilotRessource {

	private PilotDAO daoPilot = DAOFactory.getInstance().getPiloteDAO();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/signup")
	public Pilot createPilot(Pilot pilot) {
		if(daoPilot.put(pilot))
			System.out.println("pilot ajouter");
		else
			System.out.println("pilot pas ajouter");
		return pilot;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/signin")
	public Pilot loginpilot(Pilot pilot) {
		Pilot p=daoPilot.searchPilot(pilot);
		if(p==null)
			System.out.println("je ne t'ai pas trouvé");
		else
			System.out.println("autorisation d'entré");
		return p;
	}
	
}