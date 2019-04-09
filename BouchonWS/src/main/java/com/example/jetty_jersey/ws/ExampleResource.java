package com.example.jetty_jersey.ws;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.internal.util.Base64;

import com.example.jetty_jersey.model.ID;

@Path("/example")
public class ExampleResource {

	public static class ExampleClass {
		public String field;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/aircraft")
	public ExampleClass getExample() {
		ExampleClass instance = new ExampleClass();
		instance.field = "Test";

		return instance;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/aircraft")
	public void retrieveExample(ExampleClass instance) {
		System.out.println(instance.field);
	}
	
	@POST
	@RolesAllowed("ADMIN")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/encode")
	public String encode(ID id) {
	    return "";
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/decode")
	public String decode(ID id) {
	    String token = id.getId();
	    token = token.replaceFirst("Authorization ", "");
	    System.out.println(token);
	    token = Base64.decodeAsString(token);
	    return "{" + "\"status\":\"200\"," + "\"token\":\"" + token + "\"" + "}";
	}

}
