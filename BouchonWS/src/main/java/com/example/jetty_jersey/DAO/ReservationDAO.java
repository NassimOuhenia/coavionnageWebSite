package com.example.jetty_jersey.DAO;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;

import com.example.jetty_jersey.model.Flight;
import com.example.jetty_jersey.model.Reservation;

public class ReservationDAO extends DAO<Reservation> {
	
	public DAOFactory daofactory;
	
	public ReservationDAO(DAOFactory f) {
		daofactory = f;
	}
	

	@Override
	public String put(Reservation r) {
	    TransportClient  client = DAOFactory.getConnextion();
		try { 
		    IndexResponse response = client.prepareIndex("book","_doc").setSource(
			    jsonBuilder()
			    	.startObject()
			    		.field("idFlight", r.getIdFlight())
					.field("idPassenger", r.getIdPassenger())
					.field("numberPlace", r.getNumberPlace())
					.field("confirmed", "0")
				.endObject()
				).get(); 
		    if (response.status() == RestStatus.CREATED) {
			return "{" +
			    	"\"status\":\"201\"," +
			    	"\"message\":\"Well booked\"" +
			    	"}";
		    }
		}catch (IOException e ) { 
			e.printStackTrace();
		}
		return "{" +
	    		"\"status\":\"400\"," +
	    		"\"error\":\"Can not book the flight\"" +
	    		"}";
	}

	@Override
	public boolean delete(Reservation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Reservation r, String idReservation) {
	    TransportClient client = DAOFactory.getConnextion();
		try {
			UpdateResponse update = client.prepareUpdate("book", "_doc", idReservation)
					.setDoc(jsonBuilder()
							.startObject()
							.field("confirmed","1")
							.endObject()).get();
			if(update.status() == RestStatus.OK) {
			    return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Reservation> get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> get(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Reservation> getListPassenger(String idPassenger) {
	    TransportClient client = daofactory.getConnextion();
	    
	    ArrayList<Reservation> list = new ArrayList<Reservation>();
	    
	    SearchResponse response = client.prepareSearch("book")
		    .setTypes("_doc")
		    	.setQuery(QueryBuilders.matchAllQuery())
		    	.setSize(10000)
		    	.get();
	    
	    SearchHit[] result = response.getHits().getHits();
	    
	    for (int i = 0; i < result.length; i++) {
		
		Map<String, Object> map = result[i].getSourceAsMap();
		
		if (map.get("idPassenger").toString().equals(idPassenger) &&
			map.get("confirmed").toString().equals("0")) {
		    Reservation r = new Reservation(idPassenger,
			    map.get("idFlight").toString(),
			    Integer.parseInt(map.get("numberPlace").toString()));
		    list.add(r);
		}
	    }
	    
	    return list;
	}
	
	public List<Reservation> getListPassengerConfirmed(String idPassenger) {
	    TransportClient client = daofactory.getConnextion();
	    
	    ArrayList<Reservation> list = new ArrayList<Reservation>();
	    
	    SearchResponse response = client.prepareSearch("book")
		    .setTypes("_doc")
		    	.setQuery(QueryBuilders.matchAllQuery())
		    	.setSize(10000)
		    	.get();
	    
	    SearchHit[] result = response.getHits().getHits();
	    
	    for (int i = 0; i < result.length; i++) {
		
		Map<String, Object> map = result[i].getSourceAsMap();
		
		if (map.get("idPassenger").toString().equals(idPassenger) &&
			map.get("confirmed").toString().equals("1")) {
		    Reservation r = new Reservation(idPassenger,
			    map.get("idFlight").toString(),
			    Integer.parseInt(map.get("numberPlace").toString()));
		    list.add(r);
		}
	    }
	    
	    return list;
	}
	
	public List<Reservation> getListPilot(String emailPilot) {
	    TransportClient client = daofactory.getConnextion();
	    
	    ArrayList<Reservation> list = new ArrayList<Reservation>();
	    
	    SearchResponse response = client.prepareSearch("book")
		    .setTypes("_doc")
		    	.setQuery(QueryBuilders.matchAllQuery())
		    	.setSize(10000)
		    	.get();
	    
	    SearchHit[] result = response.getHits().getHits();
	    
	    for (int i = 0; i < result.length; i++) {
		
		Map<String, Object> map = result[i].getSourceAsMap();
		
		FlightDAO daoflight = new FlightDAO(daofactory);
		Flight f = daoflight.get(map.get("idFlight").toString()).get(0);
		
		String emailP = f.getPilot().getMail();
		
		if (emailP.equals(emailPilot) &&
			map.get("confirmed").toString().equals("0")) {
		    Reservation r = new Reservation(map.get("idPassenger").toString(),
			    map.get("idFlight").toString(),
			    Integer.parseInt(map.get("numberPlace").toString()));
		    list.add(r);
		}
	    }
	    
	    return list;
	}
	
	public List<Reservation> getListPilotConfirmed(String emailPilot) {
	    TransportClient client = daofactory.getConnextion();
	    
	    ArrayList<Reservation> list = new ArrayList<Reservation>();
	    
	    SearchResponse response = client.prepareSearch("book")
		    .setTypes("_doc")
		    	.setQuery(QueryBuilders.matchAllQuery())
		    	.setSize(10000)
		    	.get();
	    
	    SearchHit[] result = response.getHits().getHits();
	    
	    for (int i = 0; i < result.length; i++) {
		
		Map<String, Object> map = result[i].getSourceAsMap();
		
		FlightDAO daoflight = new FlightDAO(daofactory);
		Flight f = daoflight.get(map.get("idFlight").toString()).get(0);
		
		String emailP = f.getPilot().getMail();
		
		if (emailP.equals(emailPilot) &&
			map.get("confirmed").toString().equals("1")) {
		    Reservation r = new Reservation(map.get("idPassenger").toString(),
			    map.get("idFlight").toString(),
			    Integer.parseInt(map.get("numberPlace").toString()));
		    list.add(r);
		}
	    }
	    
	    return list;
	}

}
