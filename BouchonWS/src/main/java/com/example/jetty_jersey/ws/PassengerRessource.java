package com.example.jetty_jersey.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.jetty_jersey.model.Passenger;

@Path("/search")
public class PassengerRessource {

	List<Passenger> listPassenger=new ArrayList<Passenger>();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/passenger")
	public List<Passenger> getExample() {
		return listPassenger;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/passenger")
	public void retrieveExample(Passenger p) {
		System.out.println("ajouté : "+p.getMail());
		listPassenger.add(p);
	}
	
}