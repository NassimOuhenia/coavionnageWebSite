package com.example.jetty_jersey.DAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.rest.RestStatus;

import static org.elasticsearch.common.xcontent.XContentFactory.*;

import com.example.jetty_jersey.model.Pilot;

public class PilotDAO extends DAO<Pilot> {
	private static List<Pilot> list=new ArrayList<Pilot>();
	//un fois la bdd est en place
	private DAOFactory daofactory;

	public PilotDAO(DAOFactory f) {

		daofactory = f;
	}

	@Override
	public IndexResponse put(Pilot obj) {
		TransportClient client = daofactory.getConnextion();
		try {
		    IndexResponse response = client.prepareIndex("pilot", "_doc").setSource(
			    jsonBuilder()
			    	.startObject()
			    		.field("lastName", obj.getLastName())
			    		.field("firstName", obj.getLastName())
			    		.field("email", obj.getMail())
			    		.field("password", obj.getPassword())
			    		.field("experience", obj.getExperience())
			    		.field("qualification", obj.getCertificate())
			    	.endObject()
			    ).get();
		    return response;
		} catch(IOException e) {
		    e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(Pilot obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Pilot obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Pilot> get() {
		return list;
	}

}
