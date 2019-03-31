package com.example.jetty_jersey.ws;


import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.rest.RestStatus;

import com.example.jetty_jersey.DAO.DAO;
import com.example.jetty_jersey.DAO.DAOFactory;
import com.example.jetty_jersey.model.Pilot;
import com.example.jetty_jersey.model.ID;


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
	    return daoPilot.put(p);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get")
	public List<Pilot> getPilot(ID identification) {
	    return daoPilot.get(identification.getId());
	}
	
}