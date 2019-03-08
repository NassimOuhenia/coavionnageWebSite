package com.example.jetty_jersey.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.jetty_jersey.model.Pilot;

@Path("/search")
public class PilotRessource {

	List<Pilot> listPilot=new ArrayList<Pilot>();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/pilot")
	public List<Pilot> getExample() {
		return listPilot;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/pilot")
	public void retrieveExample(Pilot p) {
		System.out.println("ajouté : "+p.getMail()+" "+p.getExperiences());
		listPilot.add(p);
	}
	
}