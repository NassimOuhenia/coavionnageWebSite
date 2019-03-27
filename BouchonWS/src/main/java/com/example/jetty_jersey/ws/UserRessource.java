package com.example.jetty_jersey.ws;



import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.rest.RestStatus;

import com.example.jetty_jersey.DAO.DAOFactory;
import com.example.jetty_jersey.DAO.PassengerDAO;
import com.example.jetty_jersey.model.Passenger;
import com.example.jetty_jersey.model.ID;

@Path("/passengers")
public class UserRessource {

	private PassengerDAO daoPassenger = DAOFactory.getInstance().getPassengerDAO();

	@POST
	@Path("/get")
	public String getUser(ID identification) {
	    Map<String, Object> map = daoPassenger.get(identification.getId());
	    if (map.size() > 0) {
		return "{" +
			    "\"status\":\"200\"," +
			    "\"firstName\":\"" + map.get("firstName") + "\"" +
			    "\"lastName\":\"" + map.get("lastName") + "\"" +
			    "\"mail\":\"" + map.get("mail") + "\"" +
			    "}";
	    }
	    return "{" +
	    	"\"status\":\"400\"," +
	    	"\"error\":\"Can not find user\"" +
	    	"}";
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/passenger")
	public String retrieveExample() {
		System.out.println("ajouté : ");
		return "ok";
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/signup")
	public String signup(Passenger p) {
	    IndexResponse response = daoPassenger.put(p);
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