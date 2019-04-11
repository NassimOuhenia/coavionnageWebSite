package com.example.jetty_jersey.DAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.mindrot.jbcrypt.BCrypt;

import static org.elasticsearch.common.xcontent.XContentFactory.*;
import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

import com.example.jetty_jersey.JwTokenHelper;
import com.example.jetty_jersey.model.Connection;
import com.example.jetty_jersey.model.Pilot;

public class PilotDAO extends DAO<Pilot> {
	private static List<Pilot> list = new ArrayList<Pilot>();
	// un fois la bdd est en place
	public DAOFactory daofactory;

	public PilotDAO(DAOFactory f) {

		daofactory = f;
	}

	@Override
	public String put(Pilot obj) {
		TransportClient client = DAOFactory.getConnextion();
		try {
		    String certificate = obj.getCertificate();
		    if (obj.getCertificate() == null)
			certificate = "aucune";
			IndexResponse response = client.prepareIndex("pilot", "_doc")
					.setSource(jsonBuilder().startObject().field("lastName", obj.getLastName())
							.field("firstName", obj.getFirstName()).field("mail", obj.getMail())
							.field("password", BCrypt.hashpw(obj.getPassword(), BCrypt.gensalt())).field("experience", obj.getExperience())
							.field("certificate", certificate).endObject())
					.get();
			if (response.status() == RestStatus.CREATED) {
				return "{" + "\"status\":\"201\"," + "\"id\":\"" + response.getId() + "\"" + "}";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "{" + "\"status\":\"500\"," + "\"error\":\"User couldnt be created \"" + "}";
	}

	@Override
	public boolean delete(Pilot obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Pilot obj, String idPilot) {
		TransportClient client = DAOFactory.getConnextion();
		try {
			client.prepareUpdate("passenger", "_doc", idPilot)
					.setDoc(jsonBuilder().startObject()
						.field("lastName", obj.getLastName())
						.field("firstName", obj.getLastName())
						.field("mail", obj.getMail())
						//.field("password", BCrypt.hashpw(obj.getPassword(), BCrypt.gensalt()))
						.field("password", obj.getPassword())
						.field("experience", obj.getExperience())
						.field("certificate", obj.getCertificate()).endObject())
					.get();
			return true;
		} catch (IOException e) {
			
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Pilot> get() {
		return list;
	}

	public List<Pilot> get(String id) {
		TransportClient client = DAOFactory.getConnextion();
		GetResponse get = client.prepareGet("pilot", "_doc", id).get();
		ArrayList<Pilot> list = new ArrayList<Pilot>();
		if (get.isSourceEmpty()) {
			return list;
		}
		Map<String, Object> map = get.getSource();
		Pilot p = new Pilot(map.get("firstName").toString(), map.get("lastName").toString(), map.get("mail").toString(),
				map.get("password").toString(), Integer.parseInt(map.get("experience").toString()),
				map.get("certificate").toString());
		list.add(p);
		return list;
	}

	public String connect(Connection c) {
		TransportClient client = DAOFactory.getConnextion();

		SearchResponse response = client.prepareSearch("pilot").setTypes("_doc").setQuery(matchAllQuery()).setSize(10000).get();

		SearchHit[] result = response.getHits().getHits();
		for (int i = 0; i < result.length; i++) {
		    Map<String, Object> map = result[i].getSourceAsMap();
		    if (map.get("mail").toString().equals(c.getMail())) {
			if (BCrypt.checkpw(c.getPassword(), map.get("password").toString())) {
			    String token = JwTokenHelper.getInstance().generatePrivateKey(result[i].getId(), "pilot");
			    return "{" + "\"status\":\"200\"," +
				    "\"id\":\"" + token + "\"" + "}";
			}
			return "{" + "\"status\":\"400\"," + "\"error\":\"Wrong password\"" + "}";
		    }
		}
		return "{" + "\"status\":\"404\"," + "\"error\":\"User not found \"" + "}";
	}
	
	public boolean checkEmailExist(String mail) {
	    TransportClient client = daofactory.getConnextion();
	    
	    SearchResponse response = client.prepareSearch("pilot")
		    .setTypes("_doc")
		    	.setQuery(matchAllQuery())
		    	.setSize(10000)
		    .get();
	    
	    SearchHit[] result = response.getHits().getHits();
	    for (int i = 0; i < result.length; i++) {
		Map<String, Object> map = result[i].getSourceAsMap();
		if (map.get("mail").equals(mail)) {
		    return true;
		}
	    }
	    return false;
	}

}