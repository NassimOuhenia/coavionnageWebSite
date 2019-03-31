package com.example.jetty_jersey.DAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
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
	public String put(Pilot obj) {
		TransportClient client = daofactory.getConnextion();
		try {
		    IndexResponse response = client.prepareIndex("pilot", "_doc").setSource(
			    jsonBuilder()
			    	.startObject()
			    		.field("lastName", obj.getLastName())
			    		.field("firstName", obj.getLastName())
			    		.field("mail", obj.getMail())
			    		.field("password", obj.getPassword())
			    		.field("experience", obj.getExperience())
			    		.field("certificate", obj.getCertificate())
			    	.endObject()
			    ).get();
		    if (response.status() == RestStatus.CREATED) {
			return "{" +
				    "\"status\":\"201\"," +
				    "\"id\":\"" + response.getId() + "\"" +
				    "}";
		    }
		} catch(IOException e) {
		    e.printStackTrace();
		}
		return "{" +
		    "\"status\":\"500\"," +
		    "\"error\":\"User couldnt be created \"" +
		    "}";
	}

	@Override
	public boolean delete(Pilot obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Pilot obj, String idPilot) {
		TransportClient client = daofactory.getConnextion();
		try {
			UpdateResponse update = client.prepareUpdate("passenger", "_doc", idPilot)
					.setDoc(jsonBuilder()
							.startObject()
							.field("lastName", obj.getLastName())
				    		.field("firstName", obj.getLastName())
				    		.field("mail", obj.getMail())
				    		.field("password", obj.getPassword())
				    		.field("experience", obj.getExperience())
				    		.field("certificate", obj.getCertificate())
							.endObject()).get();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Pilot> get() {
		return list;
	}
	
	public List<Pilot> get(String id) {
	    TransportClient client = daofactory.getConnextion();
	    GetResponse get = client.prepareGet("pilot", "_doc", id).get();
	    ArrayList<Pilot> list = new ArrayList<Pilot>();
	    if(get.isSourceEmpty()) {
		return list;
	    }
	    Map<String, Object> map = get.getSource();
	    Pilot p = new Pilot(map.get("firstName").toString(), map.get("lastName").toString(),
		    map.get("mail").toString(), map.get("password").toString(), Integer.parseInt(map.get("experience").toString()),
		    map.get("certificate").toString());
	    list.add(p);
	    return list;
	}

}
