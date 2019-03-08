package com.example.jetty_jersey.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.jetty_jersey.DAO.PlaneDAO;
import com.example.jetty_jersey.model.Plane;

@Path("/search")
public class PlaneRessource {

	//PlaneDAO dao=;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/plane{id}")
	public List<Plane> getExample(@PathParam("id") int id) {
		//listPlane.add(new Plane(5,"rer","rer",4));
		System.out.println(id);
		return null;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/plane")
	public void retrieveExample(Plane p){
		
		System.out.println();
		System.out.println("ajouté : "+p.getNumberplace()+" "+p.getName()+" "+p.getModele());
		//listPlane.add(p);
	}
	
}