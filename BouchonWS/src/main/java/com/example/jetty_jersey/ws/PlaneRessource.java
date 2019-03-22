package com.example.jetty_jersey.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.rest.RestStatus;

import com.example.jetty_jersey.DAO.DAO;
import com.example.jetty_jersey.DAO.DAOFactory;
import com.example.jetty_jersey.model.Plane;

@Path("/plClientanes")
public class PlaneRessource {
	
	private DAO<Plane> daoPlane = DAOFactory.getInstance().getPlaneDAO();
		
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/plane/{id}")
	public Plane getExample(@PathParam("id") int id) {
		
		System.out.println(id);
		//just test
		return daoPlane.get().get(0);
		
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/add")
	public String put(Plane p) {
		IndexResponse response = daoPlane.put(p);
		if (response != null) {
			if (response.status() == RestStatus.CREATED) {
			    return "{" +
				    "\"status\":\"201\"," +
				    "\"id\":\"" + response.getId() + "\"" +
				    "}";
			}
		}
		return "{" +
		    "\"status\":\"500\"," +
		    "\"error\":\"Plane couldnt be created \"" +
		    "}";
	}
	
}