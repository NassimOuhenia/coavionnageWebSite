package com.example.jetty_jersey.DAO;

import java.beans.Expression;
import java.io.IOException; 
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;

import static org.elasticsearch.index.query.QueryBuilders.*;

import static org.elasticsearch.common.xcontent.XContentFactory.*;

import com.example.jetty_jersey.model.Connection;
import com.example.jetty_jersey.model.Passenger;
import com.example.jetty_jersey.model.Pilot;


public class PassengerDAO extends DAO<Passenger>{
	private static List<Passenger> list=new ArrayList<Passenger>();
	//un fois la bdd est en place
	private DAOFactory daofactory;

	public PassengerDAO(DAOFactory f) {

		daofactory = f;
	}

	@Override
	public String put(Passenger obj) {
		TransportClient client = daofactory.getConnextion();  
		try { 
			IndexResponse response = client.prepareIndex("passenger", "_doc").setSource(
					jsonBuilder()
					.startObject()
					.field("firstName",obj.getFirstName())
					.field("lastName",obj.getLastName())
					.field("mail",obj.getMail())
					.field("password",obj.getPassword())
					.endObject()
					).get(); 
			if (response.status() == RestStatus.CREATED) {
			    return "{" +
				    "\"status\":\"201\"," +
				    "\"id\":\"" + response.getId() + "\"" +
				    "}";
			}
		}
		catch (IOException e) { 
			e.printStackTrace();
		}
		return "{" +
	    		"\"status\":\"500\"," +
	    		"\"error\":\"Plane couldnt be created \"" +
	    		"}";
	}

	@Override
	public boolean delete(Passenger obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Passenger obj, String idPassenger) {
		TransportClient client = daofactory.getConnextion();
		try {
			UpdateResponse update = client.prepareUpdate("passenger", "_doc", idPassenger)
					.setDoc(jsonBuilder()
							.startObject()
							.field("firstName",obj.getFirstName())
							.field("lastName",obj.getLastName())
							.field("mail",obj.getMail())
							.field("password",obj.getPassword())
							.endObject()).get();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
	
	
	public List<Passenger> get(String id) {
	    TransportClient client = daofactory.getConnextion();
	    GetResponse get = client.prepareGet("passenger", "_doc", id).get();
	    ArrayList<Passenger> list = new ArrayList<Passenger>();
	    if (get.isSourceEmpty()) {
		return list;
	    }
	    Map<String, Object> map = get.getSource();
	    Passenger p = new Passenger(map.get("firstName").toString(), map.get("lastName").toString(), map.get("mail").toString(), map.get("password").toString());
	    list.add(p);
	    return list;
	}

	@Override
	public List<Passenger> get() {
		
		return list;
	}
	
	
	public Passenger SearchPassenger(Passenger p) {
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getMail().equals(p.getMail())&& list.get(i).getPassword().equals(p.getPassword())  ) {
				return list.get(i);
			}
			
		}
		return null;
	}

	public String connect(Connection c) {
	    TransportClient client = DAOFactory.getConnextion();

	    SearchResponse response = client.prepareSearch("passenger").setTypes("_doc").setQuery(matchAllQuery()).setSize(10000).get();

	    SearchHit[] result = response.getHits().getHits();
	    System.out.println(c.getMail() + " " + c.getPassword());
	    for (int i = 0; i < result.length; i++) {
		Map<String, Object> map = result[i].getSourceAsMap();
		if (map.get("mail").toString().equals(c.getMail())) {
		    //if (BCrypt.checkpw(c.getPassword(), map.get("password").toString())) {
		    if (map.get("password").toString().equals(c.getPassword())) {
			System.out.println(map.get("mail"));
			return "{" + "\"status\":\"200\"," +
				// Mettre a la place le token
				"\"id\":\"" + result[i].getId() + "\"" + "}";
		    }
		    return "{" + "\"status\":\"400\"," + "\"error\":\"Wrong password\"" + "}";
		}
	    }
	    return "{" + "\"status\":\"404\"," + "\"error\":\"User not found \"" + "}";
	}
	
	public boolean checkEmailExist(String mail) {
	    TransportClient client = daofactory.getConnextion();
	    
	    SearchResponse response = client.prepareSearch("passenger")
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