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
import com.example.jetty_jersey.model.Pilot;
import com.example.jetty_jersey.model.User;


@Path("/user/pilots/")
public class PilotRessource {

	private DAO<Pilot> daoPilot = DAOFactory.getInstance().getPiloteDAO();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/signup")
	public List<Pilot> getExample(User pilote) {
		return daoPilot.get();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/signin")
	public void retrieveExample() {
		
		daoPilot.put(new Pilot(" "," ","m","**",12,"a"));
	}
	
}