package com.example.jetty_jersey.DAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;

import com.example.jetty_jersey.model.*;
import static org.elasticsearch.common.xcontent.XContentFactory.*;

public class FlightDAO extends DAO<Flight> {
	private static List<Flight> list=new ArrayList<Flight>();
	//un fois la bdd est en place
	private DAOFactory daofactory;

	public FlightDAO(DAOFactory f) {
		list.add(new Flight("1",new Date(), "orly", "CDG", 35, 2, "travel", new Plane(), new Pilot()));
		list.add(new Flight("2",new Date(), "orly", "CDG", 35, 2, "travel", new Plane(), new Pilot()));
		list.add(new Flight("3",new Date(), "paris", "paris", 35, 2, "travel", new Plane(), new Pilot()));
		
		daofactory = f;
	}
	

	@Override
	public IndexResponse put(Flight obj) {
		TransportClient  client = daofactory.getConnextion(); 
		try { 
			IndexResponse response = client.prepareIndex("flight","_doc").setSource(
					jsonBuilder()
					.startObject()
					.field("date",obj.getDate())
					.field("departureAirport",obj.getDeparture_airport())
					.field("arrivalAirport",obj.getArrival_airport())
					.field("travelTime",obj.getTravelTime())
					.field("price",obj.getPrice())
					.field("typeflight",obj.getTypeflight())
					.field("plane",obj.getPlane())
					.field("pilot",obj.getPilot())
					.field("passagers",obj.getPassagers())
					.endObject()
					).get(); 
			return response; 
		}catch (IOException e ) { 
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(Flight obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Flight obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Flight> get() {
		
		return list;
	}
	
	public List<Flight> search(String local, String travel, Date date, String departure, String arrival) { 
	
		ArrayList<Flight> l = new ArrayList <Flight>();
		for(Flight f : list) {
			if(f.getArrival_airport().equals(arrival)
				&& f.getDeparture_airport().equals(departure))
				l.add(f);
		}
		return l;
	}


	@Override
	public Map<String, Object> get(String id) {
	    TransportClient client = daofactory.getConnextion();
	    return client.prepareGet("flight", "_doc", id).get().getSource();
	}

	
	public IndexResponse book (String idFlight, String idPassenger, int numberPlace) {
	    TransportClient  client = daofactory.getConnextion(); 
		try { 
		    IndexResponse response = client.prepareIndex("book","_doc").setSource(
			    jsonBuilder()
			    	.startObject()
			    		.field("idFlight", idFlight)
					.field("idPassenger", idPassenger)
					.field("numberPlace", numberPlace)
					.field("confrimed", 0)
				.endObject()
				).get(); 
			return response; 
		}catch (IOException e ) { 
			e.printStackTrace();
		}
		return null;
	}
	
	public SearchResponse get(String typeFlight, Date date, String departure, String arrival) {
	    TransportClient client = daofactory.getConnextion();
	    
	    SearchResponse response = client.prepareSearch("flight")
		    .setTypes("_doc")
		    	.setQuery(QueryBuilders.termQuery("typeflight", typeFlight))
			.setQuery(QueryBuilders.termQuery("departureAirport", departure))
			.setQuery(QueryBuilders.termQuery("arrivalAirport", arrival))
			.setQuery(QueryBuilders.termQuery("date", date))
			.get();
	    
	    return response;
	    
	}
	
	public UpdateResponse updateBook(String idPassenger) {
		TransportClient client = daofactory.getConnextion();
		try {
			UpdateResponse update = client.prepareUpdate("book", "_doc", idPassenger)
					.setDoc(jsonBuilder()
							.startObject()
							.field("confirmed",1)
							.endObject()).get();
			return update;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	
}

