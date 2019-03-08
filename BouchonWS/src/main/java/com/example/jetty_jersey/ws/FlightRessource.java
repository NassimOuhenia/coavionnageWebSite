package com.example.jetty_jersey.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.jetty_jersey.model.Flight;

@Path("/search")
public class FlightRessource {

	List<Flight> listFlight=new ArrayList<Flight>();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/flight")
	public List<Flight> getExample() {
		return listFlight;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/flight")
	public void retrieveExample(Flight p) {
		System.out.println("ajouté : flight");
		listFlight.add(p);
	}
	
}