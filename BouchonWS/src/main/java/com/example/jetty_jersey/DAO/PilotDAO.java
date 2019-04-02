package com.example.jetty_jersey.DAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;

import static org.elasticsearch.common.xcontent.XContentFactory.*;

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
			IndexResponse response = client.prepareIndex("pilot", "_doc")
					.setSource(jsonBuilder().startObject().field("lastName", obj.getLastName())
							.field("firstName", obj.getFirstName()).field("mail", obj.getMail())
							.field("password", obj.getPassword()).field("experience", obj.getExperience())
							.field("certificate", obj.getCertificate()).endObject())
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
					.setDoc(jsonBuilder().startObject().field("lastName", obj.getLastName())
							.field("firstName", obj.getLastName()).field("mail", obj.getMail())
							.field("password", obj.getPassword()).field("experience", obj.getExperience())
							.field("certificate", obj.getCertificate()).endObject())
					.get();
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

		QueryBuilder query = QueryBuilders.queryStringQuery("mail:'" + c.getMail() + "'");

		SearchResponse response = client.prepareSearch("pilot").setTypes("_doc").setQuery(query).get();

		SearchHit[] result = response.getHits().getHits();
		if (result.length == 0) {
			return "{" + "\"status\":\"404\"," + "\"error\":\"User not found \"" + "}";
		} else if (result.length > 1) {
			return "{" + "\"status\":\"500\"," + "\"error\":\"Multiply mail \"" + "}";
		} else {
			Map<String, Object> map = result[0].getSourceAsMap();
			if (map.get("password").equals(c.getPassword())) {
				return "{" + "\"status\":\"200\"," +
				// Mettre a la place le token
						"\"id\":\"" + result[0].getId() + "\"" + "}";
			} else {
				return "{" + "\"status\":\"400\"," + "\"error\":\"Wrong password\"" + "}";
			}
		}
	}
	
	public boolean checkEmailExist(String mail) {
	    TransportClient client = daofactory.getConnextion();
	    
	    QueryBuilder query = QueryBuilders.queryStringQuery("mail:'" + mail + "'");
	    
	    SearchResponse response = client.prepareSearch("passenger")
		    .setTypes("_doc")
		    	.setQuery(query)
		    .get();
	    
	    SearchHit[] result = response.getHits().getHits();
	    if (result.length == 1) {
		return true;
	    }
	    return false;
	}

}