package com.example.jetty_jersey.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.example.jetty_jersey.DAO.DAOFactory;
import com.example.jetty_jersey.DAO.PilotDAO;
import com.example.jetty_jersey.model.Pilot;
import com.example.jetty_jersey.model.Connection;
import com.example.jetty_jersey.model.ID;

@Path("/user/pilots/")
public class PilotRessource {

	private PilotDAO daoPilot = DAOFactory.getInstance().getPiloteDAO();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/signup")
	public String signUp(Pilot p) {
	    if (daoPilot.checkEmailExist(p.getMail())) {
		return "{" +
	    		"\"status\":\"400\"," +
	    		"\"error\":\"Email already used\"" +
	    		"}";
	    }
		return daoPilot.put(p);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get")
	public List<Pilot> getPilot(ID identification) {
		return daoPilot.get(identification.getId());
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/signin")
	public String signIn(Connection c) {
		return daoPilot.connect(c);
	}

}