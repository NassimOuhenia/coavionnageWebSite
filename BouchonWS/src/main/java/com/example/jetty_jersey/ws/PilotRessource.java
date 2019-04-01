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
import com.example.jetty_jersey.DAO.PilotDAO;
import com.example.jetty_jersey.model.Pilot;
import com.example.jetty_jersey.model.User;
import com.example.jetty_jersey.model.ID;


@Path("/user/pilots/")
public class PilotRessource {

	private PilotDAO daoPilot = DAOFactory.getInstance().getPiloteDAO();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/signup")
	public Pilot createPilot(User pilote) {
		Pilot pilot=new Pilot(pilote.getFirstName(), pilote.getLastName(), pilote.getMail(), pilote.getPassword(),
				0, "");
		
		if(daoPilot.put(pilot)) {
			//System.out.println("hhhhh");
		return pilot;	
		}else {
			return null;
		}
		
	
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/signin")
	public Pilot loginpilot(User pilote) {
		
		Pilot pilot=new Pilot(pilote.getFirstName(), pilote.getLastName(), pilote.getMail(), pilote.getPassword(),
				0, "");
		
		return daoPilot.Searchpilote(pilot);
		
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