package com.example.jetty_jersey.ws;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.elasticsearch.action.index.IndexResponse;

import com.example.jetty_jersey.DAO.DAO;
import com.example.jetty_jersey.DAO.DAOFactory;
import com.example.jetty_jersey.model.Pilot;


@Path("/pilots")
public class PilotRessource {

	private DAO<Pilot> daoPilot = DAOFactory.getInstance().getPiloteDAO();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/pilot")
	public List<Pilot> getExample() {
		return daoPilot.get();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/pilot")
	public void retrieveExample() {
		
		daoPilot.put(new Pilot(" "," ","m","**",12,"a"));
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/signup")
	public String signUp(Pilot p) {
	    IndexResponse response = daoPilot.put(p);
	    if (response )
	}
	
}